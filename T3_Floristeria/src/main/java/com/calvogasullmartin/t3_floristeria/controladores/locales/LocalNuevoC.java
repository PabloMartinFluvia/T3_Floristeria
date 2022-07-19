package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalUnidadesC;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import java.io.IOException;

public class LocalNuevoC extends LocalUnidadesC implements NuevoC{    
    
    public LocalNuevoC(Manager manager) {
        super(manager);
    }
    
    @Override
    public void aceptar(AppCVisitor controlador) {
        controlador.visitar(this);
    }
    
    @Override
    public void setCategoriaIdx(int enumIndex) {
        assert enumIndex >= 0 && enumIndex < this.getNUM_CATEGORIAS();
        productoUnidad.getProducto().setCategoria(Categoria.values()[enumIndex]);
    }
    
    @Override
    public void setPrecio(float precio) {
        assert precio > 0;
        productoUnidad.getProducto().setPrecio(precio);
    }
    
    @Override
    public void setAlturaIdx(int alturaIndex) {
        assert alturaIndex >= 0 && alturaIndex <Altura.values().length;
        productoUnidad.getProducto().setAltura(Altura.values()[alturaIndex]);
    }

    @Override
    public void setColor(String color) {
        assert color != null;
        assert color.length() >0 && color.length()<21;
        productoUnidad.getProducto().setColor(color);
    }

    @Override
    public void setMaterialIdx(int materialIndex) {
        assert materialIndex >= 0 && materialIndex <Material.values().length;
        productoUnidad.getProducto().setMaterial(Material.values()[materialIndex]);
    }
    
    @Override
    public boolean estaEnStock() throws IOException {        
        int producto_id = factory.getProductoCompletoDao().getProductoId(productoUnidad.getProducto());
        if(producto_id == 0){
            productoUnidad.setCantidad(-1);
            /*
            not found en ningun stock
            not found en productos table
            not foun en productos collection            
            */
            return false;
        }else{
            productoUnidad.getProducto().setProducto_id(producto_id);
            int cantidad = factory.getProductoUnidadDao().
                    getCantidadEnStockBy(getStockId(), producto_id);   
            //si no se encuentra el dao devuelve un número negativo
            productoUnidad.setCantidad(cantidad);
            if (cantidad >= 0){                
                //existe el producto, y tiene una cantidad >= 0 en su stock                                
                return true;
            }else{
                /*
                existe el producto, xo no tiene una cantidad >= 0 asociada en su estock.
                Solo se puede dar cuando, anteriormente, el usuario ha pedido descatalogar el producto
                (eliminar su relación con su estock), pero no se ha borrado el registro producto
                debido a que seguía associado a algun tiquet.
                 
                    tanto en mongo como en mysql se elimina la relación con stock, pero se mantiene
                    el producto en su colección / tabla, ya que hay algun tiquet que lo referencia
                    
                    en el caso de txt, se obta por asignarle una cantidad de -1 en su stock
                    (ya que si se borrara el embebido y con posterioridad se quisiera añadir de nuevo,
                    no se encontraría, y se crearía como nuevo, con un nuevo id, perdiendo toda relación
                    con el producto embebido en el tiquet).
                
                */                
                return false;
            }
        }
    }
    
    @Override
    public void addIncrValor() {
        incrementoValor += incremento * productoUnidad.getProducto().getPrecio();
    }

    @Override
    public void addIncrValorStock() {
        incrementoValorStocks[getStockId()-1] += incremento * productoUnidad.getProducto().getPrecio();
    }

    @Override
    public void guardarNuevoProductoUnidad() throws IOException {           
        if(getOldCantidad()<0){
            //para "quitarle la etiqueta" de descatalogado o no existente
            productoUnidad.setCantidad(0);
        }
        productoUnidad.setCantidad(getOldCantidad()+incremento);
       factory.getProductoUnidadDao().createNuevoProductoUnidadEnStocks(productoUnidad, getStockId());
    }
}
