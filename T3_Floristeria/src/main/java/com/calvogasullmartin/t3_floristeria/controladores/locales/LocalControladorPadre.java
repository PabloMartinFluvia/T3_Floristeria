package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;


public abstract class LocalControladorPadre extends LocalControladorManager implements ControladorPadre{
    
    public LocalControladorPadre(Manager manager) {
        super(manager);
    }

    @Override
    public abstract void aceptar(ControladorPadreVisitor controlador);
}
