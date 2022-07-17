package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.StocksView;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.StockVista;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;

public class MostrarStocksVista extends StocksView{        
    
    public void interactuar (MostrarStocksControlador controlador){         
        int stock_index = obtenerStockIndex("que tipo de stock desea consultar");                
        controlador.setConUnidades(preguntarSiIncluirUnidades());
        try {
            mostrarStocks(controlador, stock_index);                                    
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }        
        repetir("ver algun otro stock", controlador);
    }
    
    @Override
    protected int obtenerStockIndex(String mensage) {
        return new StockVista().pedirCategoria(mensage);
    }    
    
    private boolean preguntarSiIncluirUnidades (){
        YesNoDialog requerimiento = new YesNoDialog("Desea ver los productos con las unidades");
        return requerimiento.read();
    }

    
}
