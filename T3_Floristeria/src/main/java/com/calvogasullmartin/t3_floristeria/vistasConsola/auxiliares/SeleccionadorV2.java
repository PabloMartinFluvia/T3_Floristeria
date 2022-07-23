package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC2;

public abstract class SeleccionadorV2 {
    
    private InOut io;
    
    private boolean withUnits;
    
    private boolean withProductId;
    
    private boolean isAllOptionsPosible;    
    
    public SeleccionadorV2(){
        io = new InOut();
        withUnits = true;
        withProductId = true;
        isAllOptionsPosible = false;
    }
    
    
    protected boolean seleccionarIdProducto(SeleccionadorC2 controlador, String operationOption) throws IOException {
        int stock_id = mostrarStock(controlador.getNewStocksC(),
                "el tipo de producto que desea "+operationOption);
        int productoId = 0;
        boolean out = false;
        boolean seleccionado = false;
        do{           
           productoId = io.readInt("Introduzca el ID del producto que desea "+operationOption+": ");
           controlador.resetModels();
           if(controlador.isIdValid(productoId, stock_id)){
               seleccionado = true;
               out = true;
           }else{
               YesNoDialog requerimiento = new YesNoDialog("Id seleccionado incorrecto. Desea intentarlo de nuevo");
               out = !requerimiento.read();
           }
        }while(!out);  
        return seleccionado;
    }    
    
    private int mostrarStock(StocksC2 stocks,String mensageOption) throws IOException{
        return new MostradorConjuntosV2(withUnits,withProductId).
                    showStocks(stocks, isAllOptionsPosible, mensageOption);        
    }
    
    protected abstract void step2() throws IOException;
}
