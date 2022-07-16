package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LocalAddProductoControlador extends LocalControladorPadre implements AddProductoControlador {

    private ProductoCompleto producto;

    private ProductoUnidad productoUnidad;

    private final int MAX_CANTIDAD;

    public LocalAddProductoControlador(Manager manager) {
        super(manager);
        MAX_CANTIDAD = manager.getMAX_UNIDADES_EN_STOCK();
    }

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
    @Override
    public void inicializarNuevoProducto() {
        producto = new ProductoCompleto();
        productoUnidad = new ProductoUnidad();
        productoUnidad.setProducto(producto);
    }

    @Override
    public void almacenarCategoria(int indexCategoria) {
        producto.setCategoria(Categoria.values()[indexCategoria]);
    }

    @Override
    public void almacenarPrecio(float precio) {
        producto.setPrecio(precio);
    }

    @Override
    public void almacenarAltura(int indexAltura) {
        producto.setAltura(Altura.values()[indexAltura]);
    }

    @Override
    public void almacenarColor(String color) {
        producto.setColor(color);
    }

    @Override
    public void almacenarMaterial(int indexMaterial) {
        producto.setMaterial(Material.values()[indexMaterial]);
    }

    @Override
    public void almacenarUnidadesIniciales(int cantidad) {
        assert cantidad > 0;
        productoUnidad.setCantidad(cantidad);
    }

    @Override
    public void addProductoConUnidadesEnStock() throws IOException {
        factory.getProductoUnidadesDao().createProductoYAsociarloAlStockConUnidades(productoUnidad, getStockId());
    }

    @Override
    public void actualizarValoresStock() throws IOException {
        float incrementoValor = producto.getPrecio() * productoUnidad.getCantidad();
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);
        factory.getConjuntoProductosDao().incrementarValorEnStockById(getStockId(), incrementoValor);
    }
    
    private int getStockId() {
        return producto.getCategoria().ordinal() + 1;
    }   

    @Override
    public int getMaxCantidad() {        
        return MAX_CANTIDAD;
    }

    @Override
    public boolean isNuevo() throws IOException {
        Categoria categoria = producto.getCategoria();
        List<ProductoCompleto> listaProductos = factory.getProductoCompletoDao()
                .getProductosSinUnidadesByStockId(categoria.ordinal() + 1);
        if (listaProductos == null || listaProductos.isEmpty()) {
            return true;
        } else { // hay productos de esa categoria ya guardados
            Iterator<ProductoCompleto> iterador = listaProductos.iterator();                         
            while (iterador.hasNext()) {                                
                if (sonIguales(iterador.next())) {                    
                    return false;
                }
            }
            return true;
        }
    }

    private boolean sonIguales(ProductoCompleto productoEnStock) {
        //no se compara el id, se deja en null        
        boolean iguales = productoEnStock.getPrecio() == producto.getPrecio();
        switch(producto.getCategoria()){
            case ARBOL: iguales = iguales && productoEnStock.getAltura().equals(producto.getAltura());
                break;
            case FLOR: iguales = iguales && productoEnStock.getColor().equals(producto.getColor());
                break;
            case DECORACION: iguales = iguales && productoEnStock.getMaterial().equals(producto.getMaterial());  
                break;
        }
        return iguales;
    }            

}
