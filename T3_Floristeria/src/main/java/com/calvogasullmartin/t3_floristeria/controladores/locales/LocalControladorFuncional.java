package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;


public abstract class LocalControladorFuncional extends Controlador implements ControladorFuncional{
    
    public LocalControladorFuncional(Estados estados) {
        super(estados);
    }

    @Override
    public abstract void aceptar(ControladorFuncionalVisitor controlador);
}
