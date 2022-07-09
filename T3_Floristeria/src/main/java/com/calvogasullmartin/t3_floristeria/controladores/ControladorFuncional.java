package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;

public abstract class ControladorFuncional extends Controlador implements ControladorFuncionalInterface{

    public ControladorFuncional(Aplicacion aplicacion) {
        super(aplicacion);
    }
    
    @Override
    public abstract void aceptar(ControladorFuncionalVisitor controlador);
}
