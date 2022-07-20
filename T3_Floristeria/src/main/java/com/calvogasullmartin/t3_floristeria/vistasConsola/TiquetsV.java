package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.MostradorConjuntosV;

public class TiquetsV{
    
    private InOut io;
    
    private boolean withUnits;
    
    private boolean withProductId;
    
    public TiquetsV(){
        io = new InOut();
        withUnits = true;
        withProductId = false;
    }
    
    public void interactuar(TicketsC controlador){                
        try {
            new MostradorConjuntosV(withUnits, withProductId).showTiquets(controlador);
            new YesWaiting("\nHa finalizado de ver la lista de tiquets").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        controlador.seleccionarMenu();
    }
}
