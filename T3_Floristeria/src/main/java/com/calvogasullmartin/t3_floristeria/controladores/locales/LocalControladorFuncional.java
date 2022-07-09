package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;


public abstract class LocalControladorFuncional extends Controlador implements ControladorFuncional{
    
    public LocalControladorFuncional(Estado estado) {
        super(estado);
    }

    @Override
    public abstract void aceptar(ControladorFuncionalVisitor controlador);
}
