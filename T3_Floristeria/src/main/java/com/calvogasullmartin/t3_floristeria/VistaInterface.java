package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;

public interface VistaInterface extends ControladorFuncionalVisitor{
    
    public void interactuar(ControladorFuncional controlador);
}
