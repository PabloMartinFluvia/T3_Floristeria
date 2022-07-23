package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC2;

public class ToMenuV2 {
    
    private InOut io;
    
    private PersistenciaC2 controlador;

    public ToMenuV2(PersistenciaC2 controlador) {        
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
