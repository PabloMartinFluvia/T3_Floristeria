package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;

public interface Vista extends AppCVisitor{
        
    public void interactuar(AppC controlador);
}
