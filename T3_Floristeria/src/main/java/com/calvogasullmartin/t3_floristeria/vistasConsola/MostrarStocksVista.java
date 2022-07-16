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
        int stock_index = obtenerStockIndex("que tipo de stock desea consultar");                
        boolean conUnidades = preguntarSiIncluirUnidades();
        try {
            mostrarStocks(controlador, stock_index, conUnidades);                        
            finalizar();
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }        
        controlador.seleccionarMenu();
    }
    
    // devuelve index (posición en array de stocks / array de categoria). devuelve -1 si todos        
    private int obtenerStockIndex(String mensage){
        return new StocksVista().pedirCategoria(mensage);
    }
    
    private boolean preguntarSiIncluirUnidades (){
        YesNoDialog requerimiento = new YesNoDialog("Desea ver los productos con las unidades");
        return requerimiento.read();
    }
    
    private void mostrarStocks(MostrarStocksControlador controlador, int stock_index, boolean conUnidades) throws IOException{
        StocksVista stocksVista = new StocksVista();  
            controlador.getStocks(stock_index);
            int numStocks = controlador.getNumConjuntosToShow();
            for (int stocksIndex = 0; stocksIndex < numStocks; stocksIndex++) {
                stocksVista.mostrarStock(controlador, stocksIndex, conUnidades);
            } 
    }
    
    private void finalizar(){
        YesNoDialog requerimiento = new YesNoDialog("Ha finalizado");
        boolean ok = false;
        do{
            ok = requerimiento.read();
        }while(!ok);             
    }
}
