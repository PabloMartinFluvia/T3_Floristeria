package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalStocksC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;

public abstract class LocalSeleccionadorC extends LocalIncrementosC implements SeleccionadorC{
    
    
    public LocalSeleccionadorC(Manager manager) {
        super(manager);
    }

    @Override
    public abstract void aceptar(AppCVisitorC controlador);

    @Override
    public StocksC getNewStocksC() {        
        return new LocalStocksC(this.getManager());
    }
    
    @Override
    public boolean isIdValid(int productoId, int stock_id) throws IOException {               
        ProductoCompleto producto = factory.getProductoCompletoDao().
                findProductoByIdInStockId(productoId, stock_id);        
        if(producto != null){
            productoUnidad.setProducto(producto);
            int cantidad = factory.getProductoUnidadDao()
                    .getCantidadEnStockBy(stock_id, productoId);
            if(cantidad >=0){
                productoUnidad.setCantidad(cantidad);
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    
    @Override
    public abstract void addIncrValor();

    @Override
    public abstract void addIncrValorStock();   
}
