package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.Controlador;

public interface Vista extends ControladorVisitador{
        
    public void interact(Controlador controlador);
}
