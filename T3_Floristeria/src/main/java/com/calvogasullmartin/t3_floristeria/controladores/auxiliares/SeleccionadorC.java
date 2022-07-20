package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import java.io.IOException;

public interface SeleccionadorC extends IncrementosC{
    
    StocksC getNewStocksC();
        
    boolean isIdValid(int productoId, int stock_id) throws IOException; //lo busca y se lo guarda
}
