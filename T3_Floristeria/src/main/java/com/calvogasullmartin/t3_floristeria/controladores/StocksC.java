package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC;
import java.io.IOException;

public interface StocksC extends ConjuntosC{
    
    /**
     * If stockIndex <0 -> all.
     * En caso contrario la lista solo tendrá un stock.
     * @param stockIndex 
     */
    void getStocks(int stockIndex) throws IOException;
}
