package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.TiquetsC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.MostrarConjuntoV;
import java.io.IOException;

public class TiquetsV extends MostrarConjuntoV {
    
    private InOut io;

    private TiquetsC controlador;
    
    public TiquetsV(TiquetsC controlador){
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interactuar(){        
        inicializar(controlador, true, true);        
        try {
            readConjunto();
            mostrarConjuntos(controlador);
            new YesWaiting("\nHa finalizado de ver la lista de tiquets").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        controlador.seleccionarMenu();
    }

    @Override
    protected void readConjunto() throws IOException {
        controlador.getAllTiquets();
    }

    
    
    
}
