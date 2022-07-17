package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.StocksView;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.StockVista;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;

public class MostrarStocksVista extends StocksView{    
    
    public MostrarStocksVista() {
        io = new InOut();
    }
    
    public void interactuar (MostrarStocksControlador controlador){         
        int stock_index = obtenerStockIndex("que tipo de stock desea consultar");                
        controlador.setConUnidades(preguntarSiIncluirUnidades());
        try {
            mostrarStocks(controlador, stock_index);                        
            esperar("Ha finalizado de ver el listado");
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }        
        controlador.seleccionarMenu();
    }
    
    @Override
    protected int obtenerStockIndex(String mensage) {
        return new StockVista().pedirCategoria(mensage);
    }
                
    private void esperar(String mensage){
        YesNoDialog requerimiento = new YesNoDialog(mensage);        
        do{            
        }while(!requerimiento.read());             
    }
    
    private boolean preguntarSiIncluirUnidades (){
        YesNoDialog requerimiento = new YesNoDialog("Desea ver los productos con las unidades");
        return requerimiento.read();
    }

    
}
