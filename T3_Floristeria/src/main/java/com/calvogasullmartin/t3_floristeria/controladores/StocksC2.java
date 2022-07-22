package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public interface StocksC2 extends ConjuntosC2{
    
    /**
     * If stockIndex <0 -> all.
     * En caso contrario la lista solo tendrá un stock.
     * @param stockIndex 
     */
    void getStocks(int stockIndex) throws IOException;
}
