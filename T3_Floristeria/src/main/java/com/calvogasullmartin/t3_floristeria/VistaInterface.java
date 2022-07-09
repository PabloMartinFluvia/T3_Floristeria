package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;

public interface VistaInterface extends ControladorFuncionalVisitor{
    
    public void interactuar(ControladorFuncional controlador);
}
