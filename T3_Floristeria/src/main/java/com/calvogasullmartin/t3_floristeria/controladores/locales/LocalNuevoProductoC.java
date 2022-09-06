package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public class LocalNuevoProductoC extends LocalGestionarUnidadesC implements NuevoProductoC {

    private boolean idemProductAlreadyInStocks;

    private boolean idemProductSoldAnytime;

    private boolean isNew;

    public LocalNuevoProductoC(Manager manager) {
        super(manager);
    }

    @Override
    public void setCategoriaIdx(int categoriaIdx) {
        assert categoriaIdx >= 0 && categoriaIdx < this.getNUM_CATEGORIAS();
        productoUnidadUpdating.getProducto().setCategoria(Categoria.values()[categoriaIdx]);
        stock_id_updating = categoriaIdx + 1;
    }

    @Override
    public void setNombre(String nombre) {
        assert nombre != null;
        assert nombre.length() >= 5 && nombre.length() <= 20;
        productoUnidadUpdating.getProducto().setNombre(nombre);
    }

    @Override
    public void setPrecio(float precio) {
        assert precio > 0f;
        productoUnidadUpdating.getProducto().setPrecio(precio);
    }

    @Override
    public void setAlturaIdx(int alturaIndex) {
        assert alturaIndex >= 0 && alturaIndex < Altura.values().length;
        productoUnidadUpdating.getProducto().setAltura(Altura.values()[alturaIndex]);
    }

    @Override
    public void setColor(String color) {
        assert color != null;
        assert color.length() >= 3 && color.length() <= 20;
        productoUnidadUpdating.getProducto().setColor(color);
    }

    @Override
    public void setMaterialIdx(int materialIndex) {
        assert materialIndex >= 0 && materialIndex < Material.values().length;
        productoUnidadUpdating.getProducto().setMaterial(Material.values()[materialIndex]);
    }

    @Override
    public void lookForIdemProduct() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha podido comprovar si ya existe "
                + "un producto con características idénticas.";        
        int producto_id = factory.getProductoCompletoDao()
                .findId(productoUnidadUpdating.getProducto());
        if (producto_id == 0) { // no existe ninguno igual
            idemProductAlreadyInStocks = false;
            idemProductSoldAnytime = false;
        } else { // existe alguno igual
            manageIdemProduct(producto_id);
        }
    }

    //existe en la BD un producto con ídem características
    private void manageIdemProduct(int producto_id) throws IOException, SQLException, MongoException {       
        productoUnidadUpdating.getProducto().setProducto_id(producto_id);
        errorBD = "Error! No se ha podido leer la cantidad en stock "
                + "del producto con características idénticas.";        
        int cantidad = factory.getProductoUnidadDao()
                .getCantidadEnStockBy(stock_id_updating, producto_id);
        if (cantidad >= 0) { //hay uno igual en stocks 
            idemProductAlreadyInStocks = true;
            //no importa si también está en tickets <- si ya está en stock no és un "new valid"
        } else { 
            //no hay ninguno igual en stock -> se ha encontrado en un ticket -> descatalogado en stocks
            idemProductAlreadyInStocks = false;
            idemProductSoldAnytime = true;
        }
    }

    @Override
    public boolean isIdemProductAlreadyInStocks() {
        return idemProductAlreadyInStocks;
    }

    @Override
    public boolean isIdemProductSoldAnytime() {
        return idemProductSoldAnytime;
    }

    @Override
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public void updateUnitsStock(int increment) throws IOException, SQLException, MongoException {
        assert increment >= 0 && increment <= getMAX_UNIDADES_EN_STOCK();
        productoUnidadUpdating.setCantidad(increment);
        if (isNew) {            
            errorBD = "Error! No se ha podido guardar el nuevo producto";
            factory.getProductoUnidadDao()
                    .createNuevoProductoUnidadEnStock(productoUnidadUpdating, stock_id_updating);
        } else {
            errorBD = "Error! No se ha podido volver a poner en catálogo el producto.";
            factory.getProductoUnidadDao()
                    .reCatalogarProductoUnidadEnStock(productoUnidadUpdating, stock_id_updating);
        }
        float canvioValor = increment * productoUnidadUpdating.precioProducto();
        variacionValorStocks[stock_id_updating - 1] += canvioValor;
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        assert controlador != null;
        controlador.visit(this);
    }
}
