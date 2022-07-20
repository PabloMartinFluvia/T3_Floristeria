package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import java.io.IOException;

public interface SeleccionadorC extends IncrementosC{
    
    StocksC getNewStocksC();
        
    boolean isIdValid(int productoId) throws IOException; //lo busca y se lo guarda
}
