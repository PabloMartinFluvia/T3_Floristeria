package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;

public abstract class ControladorFuncional extends Controlador implements ControladorFuncionalInterface{

    public ControladorFuncional(Estado estado) {
        super(estado);
    }
    
    @Override
    public abstract void aceptar(ControladorFuncionalVisitor controlador);
}
