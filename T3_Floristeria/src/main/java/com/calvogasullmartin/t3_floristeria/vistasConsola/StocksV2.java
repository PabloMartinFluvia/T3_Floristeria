package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.MostradorConjuntosV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;

public class StocksV2{
    
    private InOut io;
    
    private boolean withProductId;
    
    private boolean isAllOptionsPosible;
    
    public StocksV2(){
        io = new InOut();
        withProductId = false;
        isAllOptionsPosible = true;
    }
    
    public void interactuar(StocksC2 controlador){        
        YesNoDialog requerimiento = new YesNoDialog("Desea visualizar las unidades");                        
        try {
            new MostradorConjuntosV2(requerimiento.read(),withProductId).
                    showStocks(controlador, isAllOptionsPosible, "que stock(s) desea ver");                     
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV2(controlador).isMore("consultar de nuevo");
    }
}
