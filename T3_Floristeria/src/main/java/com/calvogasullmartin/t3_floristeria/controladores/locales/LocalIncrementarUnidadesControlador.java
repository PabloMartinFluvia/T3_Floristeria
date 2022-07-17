package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalStocksController;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesControlador;

public class LocalIncrementarUnidadesControlador extends LocalStocksController implements IncrementarUnidadesControlador{    
    
    private final int MAX_CANTIDAD;
    
    private ProductoUnidad productoUnidadTarget;
    
    private int maxIncrement;
    
    private int minIncrement;
    
    private float incrementoValor;
    
    
    public LocalIncrementarUnidadesControlador(Manager manager) {
        super(manager);    
        this.conUnidades = true;
        this.MAX_CANTIDAD = manager.getMAX_UNIDADES_EN_STOCK();
    }
    
    @Override
    public void aceptar(AppControladorVisitor visitor) {
        visitor.visitar(this);
    }       
    
    @Override
    public boolean isIdValid(int producto_id) {
        List<ProductoUnidad> listaProductos = stock.getProductos();
        Iterator<ProductoUnidad> iterador = listaProductos.iterator();
        ProductoUnidad productoEnStock;
        while (iterador.hasNext()){
            productoEnStock = iterador.next();
            if(productoEnStock.obtainProductoId() == producto_id){
                productoUnidadTarget = productoEnStock;
                maxIncrement = MAX_CANTIDAD - productoUnidadTarget.getCantidad();
                minIncrement = - productoUnidadTarget.getCantidad();
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int getMaxIncrement() {
        return maxIncrement;
    }

    @Override
    public int getMinIncrement() {
        return minIncrement;
    }

    @Override
    public void actualizarCantidad(int incremento) throws IOException{       
        setIncrementoValor(incremento);
        productoUnidadTarget.setCantidad(productoUnidadTarget.getCantidad() + incremento);
        factory.getProductoUnidadesDao().actualizarUnidadesProductoByStockId(productoUnidadTarget, getStockId());
    }
    
           
    //COMU AMB NUEVO PRODUCTO
    private void setIncrementoValor(int incremento){
        incrementoValor = productoUnidadTarget.obtainPrecioProducto()*incremento;
    }
    
    @Override
    public void incrementarValores() throws IOException {        
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);        
        factory.getConjuntoProductosDao().incrementarValorEnStockById(getStockId(), incrementoValor);
    }
    
    private int getStockId() {
        return productoUnidadTarget.obtainIndexCategoria()+ 1;
    } 

    
}
