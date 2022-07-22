package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalStocksC2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC2;

public abstract class LocalSeleccionadorC2 extends LocalIncrementosC2 implements SeleccionadorC2{
    
    
    public LocalSeleccionadorC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public abstract void aceptar(AppCVisitorC2 controlador);

    @Override
    public StocksC2 getNewStocksC() {        
        return new LocalStocksC2(this.getManager());
    }
    
    @Override
    public boolean isIdValid(int productoId, int stock_id) throws IOException {               
        ProductoCompleto2 producto = factory.getProductoCompletoDao().
                findProductoByIdInStockId(productoId, stock_id);        
        if(producto != null){
            productoUnidadSeleccionado.setProducto(producto);
            int cantidad = factory.getProductoUnidadDao()
                    .getCantidadEnStockBy(stock_id, productoId);
            if(cantidad >=0){
                productoUnidadSeleccionado.setCantidad(cantidad);
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
    
    @Override
    public abstract void modificarCantidadEnStock() throws IOException;
}
