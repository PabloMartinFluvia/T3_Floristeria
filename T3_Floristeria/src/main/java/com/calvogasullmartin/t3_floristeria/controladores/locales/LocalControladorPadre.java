package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;


public abstract class LocalControladorPadre extends LocalControladorEstado implements ControladorPadre{
    
    public LocalControladorPadre(Manager estados) {
        super(estados);
    }

    @Override
    public abstract void aceptar(ControladorPadreVisitor controlador);
}
