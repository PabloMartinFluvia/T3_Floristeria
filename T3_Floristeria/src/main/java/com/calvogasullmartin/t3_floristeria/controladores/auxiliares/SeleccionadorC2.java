package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;

public interface SeleccionadorC2 extends IncrementosC2{
    
    StocksC2 getNewStocksC();
        
    boolean isIdValid(int productoId, int stock_id) throws IOException; //lo busca y se lo guarda
    
    public void modificarCantidadEnStock() throws IOException;
}
