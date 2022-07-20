package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;


public abstract class LocalAppC extends LocalC implements AppC{

    public LocalAppC(Manager manager) {
        super(manager);
    }    
        
    @Override
    public abstract void aceptar(AppCVisitorC controlador);             
}
