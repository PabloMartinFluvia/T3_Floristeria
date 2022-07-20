package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitorC;

public interface Vista extends AppCVisitorC{
        
    public void interactuar(AppC controlador);
}
