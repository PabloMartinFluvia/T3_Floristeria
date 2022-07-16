package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;

public class MostrarStocksVista {

    private InOut io;
    
    public MostrarStocksVista() {
        io = new InOut();
    }
    
    public void interactuar (MostrarStocksControlador controlador){
        StocksVista stocksVista = new StocksVista();        
        int stock_index = stocksVista.pedirCategoria("que tipo de stock desea consultar"); 
        // devuelve index (posición en array de stocks / array de categoria). devuelve -1 si todos        
        boolean conUnidades = preguntarSiIncluirUnidades();
        try {
            controlador.getStocks(stock_index);
            int numStocks = controlador.getNumConjuntosToShow();
            for (int stocksIndex = 0; stocksIndex < numStocks; stocksIndex++) {
                stocksVista.mostrarStock(controlador, stocksIndex, conUnidades);
            }            
            finalizar();
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }        
        controlador.seleccionarMenu();
    }
    
    private boolean preguntarSiIncluirUnidades (){
        YesNoDialog requerimiento = new YesNoDialog("Desea ver los productos con las unidades");
        return requerimiento.read();
    }
    
    private void finalizar(){
        YesNoDialog requerimiento = new YesNoDialog("Ha finalizado");
        boolean ok = false;
        do{
            ok = requerimiento.read();
        }while(!ok);             
    }
}
