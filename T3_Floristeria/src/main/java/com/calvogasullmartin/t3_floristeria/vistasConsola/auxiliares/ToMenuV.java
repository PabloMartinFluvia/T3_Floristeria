package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;

public class ToMenuV {
    
    private InOut io;
    
    private PersistenciaC controlador;

    public ToMenuV(PersistenciaC controlador) {        
        this.io = new InOut();
        this.controlador = controlador;
    }
    
    public void isMore(String mensage) {
        YesNoDialog requerimiento = new YesNoDialog("Desea "+mensage+" o volver al menú");        
        if (!requerimiento.read()) {
            controlador.seleccionarMenu();
        }
    }
}
