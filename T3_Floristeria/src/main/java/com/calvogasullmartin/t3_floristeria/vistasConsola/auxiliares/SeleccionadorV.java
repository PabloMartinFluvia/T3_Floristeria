package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;

public abstract class SeleccionadorV {
    
    private InOut io;
    
    private boolean withUnits;
    
    private boolean withProductId;
    
    private boolean isAllOptionsPosible;    
    
    public SeleccionadorV(){
        io = new InOut();
        withUnits = true;
        withProductId = true;
        isAllOptionsPosible = false;
    }
    
    
    protected boolean seleccionarIdProducto(SeleccionadorC controlador, String operationOption) throws IOException {
        mostrarStock(controlador.getNewStocksC(),
                "el tipo de producto que desea"+operationOption);
        int productoId = 0;
        boolean out = false;
        boolean seleccionado = false;
        do{           
           productoId = io.readInt("Introduzca el ID del producto que desea"+operationOption);
           controlador.resetModels();
           if(controlador.isIdValid(productoId)){
               seleccionado = true;
               out = true;
           }else{
               YesNoDialog requerimiento = new YesNoDialog("Id seleccionado incorrecto. Desea intentarlo de nuevo");
               out = !requerimiento.read();
           }
        }while(!out);  
        return seleccionado;
    }    
    
    private void mostrarStock(StocksC stocks,String mensageOption) throws IOException{
        new MostradorConjuntosV(withUnits,withProductId).
                    showStocks(stocks, isAllOptionsPosible, mensageOption);        
    }
    
    protected abstract void step2() throws IOException;
}
