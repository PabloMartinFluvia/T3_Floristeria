package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.StocksController;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import java.io.IOException;

public abstract class StocksView {

    protected InOut io;

    // devuelve index (posición en array de stocks / array de categoria). devuelve -1 si todos        
    protected abstract int obtenerStockIndex(String mensage);

    protected void mostrarStocks(StocksController controlador, int stock_index) throws IOException {
        StocksVista stocksVista = new StocksVista();
        controlador.getStocks(stock_index);
        int numStocks = controlador.getNumConjuntosToShow();
        for (int stocksIndex = 0; stocksIndex < numStocks; stocksIndex++) {
            stocksVista.mostrarStock(controlador, stocksIndex);
        }
    }
}
