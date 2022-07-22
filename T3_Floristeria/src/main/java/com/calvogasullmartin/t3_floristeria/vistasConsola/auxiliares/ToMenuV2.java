package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC2;

public class ToMenuV2 {
    
    private InOut2 io;
    
    private PersistenciaC2 controlador;

    public ToMenuV2(PersistenciaC2 controlador) {        
        this.io = new InOut2();
        this.controlador = controlador;
    }
    
    public void isMore(String mensage) {
        YesNoDialog2 requerimiento = new YesNoDialog2("Desea "+mensage+" o volver al menú");        
        if (!requerimiento.read()) {
            controlador.seleccionarMenu();
        }
    }
}
