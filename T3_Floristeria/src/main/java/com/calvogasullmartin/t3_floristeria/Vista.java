package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.AppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;

public interface Vista extends AppControladorVisitor{
        
    public void interactuar(AppControlador controlador);
}
