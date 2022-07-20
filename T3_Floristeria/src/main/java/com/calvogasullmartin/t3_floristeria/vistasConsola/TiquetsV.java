package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.TiquetsC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ConjuntoProductosV;
import java.io.IOException;

public class TiquetsV {
    
    private InOut io;

    public TiquetsV() {
        io = new InOut();
    }
    
    public void interactuar(TiquetsC controlador){
        assert controlador != null;
        ConjuntoProductosV conjuntoV = new ConjuntoProductosV(controlador);
        conjuntoV.inicializar(true,false); //mostrar unidades, pero no id
        try {
            controlador.getAllTiquets();
            conjuntoV.mostrarConjuntos();
            new YesWaiting("\nHa finalizado de ver la lista de tiquets").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        controlador.seleccionarMenu();
    }
}
