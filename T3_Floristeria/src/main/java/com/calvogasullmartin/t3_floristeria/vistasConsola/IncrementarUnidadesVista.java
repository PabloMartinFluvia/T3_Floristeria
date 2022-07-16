package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import java.io.IOException;

public class IncrementarUnidadesVista {
    
    public void interacturar (IncrementarUnidadesStockControlador controlador){
        int stock_index = obtenerStockIndex
            ("la familia del producto al que se desea actualizar las unidades");
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        
        // mostrar stock con unidades
        
        //pedir el id del producto
        
        //pedir nueva cantidad
        
        //finalizar
    }
    
    // devuelve index (posición en array de stocks / array de categoria). devuelve -1 si todos        
    private int obtenerStockIndex(String mensage){
        return new StocksVista().pedirCategoria(mensage);
    }
    
    private void mostrarStocks(IncrementarUnidadesStockControlador controlador, int stock_index, boolean conUnidades) throws IOException{
        StocksVista stocksVista = new StocksVista();  
            controlador.getStocks(stock_index);
            int numStocks = controlador.getNumConjuntosToShow();
            for (int stocksIndex = 0; stocksIndex < numStocks; stocksIndex++) {
                stocksVista.mostrarStock(controlador, stocksIndex, conUnidades);
            } 
    }
}
