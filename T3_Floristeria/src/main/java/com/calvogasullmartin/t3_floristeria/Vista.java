package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;

public interface Vista extends ControladorPadreVisitor{
        
    public void interactuar(ControladorPadre controlador);
}
