package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.MostradorConjuntosV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV;
import java.io.IOException;

public class StocksV{
    
    private InOut io;
    
    private boolean withProductId;
    
    private boolean isAllOptionsPosible;
    
    public StocksV(){
        io = new InOut();
        withProductId = false;
        isAllOptionsPosible = true;
    }
    
    public void interactuar(StocksC controlador){        
        YesNoDialog requerimiento = new YesNoDialog("Desea visualizar las unidades");                        
        try {
            new MostradorConjuntosV(requerimiento.read(),withProductId).
                    showStocks(controlador, isAllOptionsPosible, "que stock(s) desea ver");                     
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV(controlador).isMore("consultar de nuevo");
    }
}
