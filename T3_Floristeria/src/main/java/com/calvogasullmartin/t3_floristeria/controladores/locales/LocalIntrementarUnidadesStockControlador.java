package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LocalIntrementarUnidadesStockControlador extends LocalStocksController implements IncrementarUnidadesStockControlador{    
    
    private final int MAX_CANTIDAD;
    
    private ProductoUnidad producto;
    
    private int maxIncrement;
    
    private int minIncrement;
    
    public LocalIntrementarUnidadesStockControlador(Manager manager) {
        super(manager);    
        this.conUnidades = true;
        this.MAX_CANTIDAD = manager.getMAX_UNIDADES_EN_STOCK();
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }    

    @Override
    public boolean isIdValid(int producto_id) {
        List<ProductoUnidad> listaProductos = stock.getProductos();
        Iterator<ProductoUnidad> iterador = listaProductos.iterator();
        ProductoUnidad productoEnStock;
        while (iterador.hasNext()){
            productoEnStock = iterador.next();
            if(productoEnStock.getProductoId() == producto_id){
                producto = productoEnStock;
                maxIncrement = MAX_CANTIDAD - producto.getCantidad();
                minIncrement = - producto.getCantidad();
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
        int oldCantidad = producto.getCantidad();
        producto.setCantidad(oldCantidad + incremento);
        factory.getProductoUnidadesDao().actualizarUnidadesProductoByStockId(producto, stock.getId());
    }

    
}
