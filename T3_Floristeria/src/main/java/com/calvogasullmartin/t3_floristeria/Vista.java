package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC2;

public interface Vista extends AppCVisitorC2{
        
    public void interactuar(AppC2 controlador);
}
