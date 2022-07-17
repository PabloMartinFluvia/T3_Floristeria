package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;

public abstract class RepetirFuncionalidadVista {

    protected InOut io;

    public RepetirFuncionalidadVista() {
        this.io = new InOut();
    }
    
    protected void repetir(String mensage, PersistenciaControlador controlador) {
        YesNoDialog requerimiento = new YesNoDialog("Desea "+mensage+" o volver al menú");
        if (!requerimiento.read()) {
            controlador.seleccionarMenu();
        }
    }
}
