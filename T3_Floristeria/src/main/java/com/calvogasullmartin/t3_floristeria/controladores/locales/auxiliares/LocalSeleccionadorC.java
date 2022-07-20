package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalStocksC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
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
    public boolean isIdValid(int productoId) throws IOException {
        //facotry find productoUnidadById in stocks
        //+ save in atribute
    }
    
    @Override
    public void addIncrValor() {
        incrementoValor += incremento * productoUnidad.getProducto().getPrecio();
    }

    @Override
    public void addIncrValorStock() {
        incrementoValorStocks[getStockId()-1] += incremento * productoUnidad.getProducto().getPrecio();
    }

    

    
    
}
