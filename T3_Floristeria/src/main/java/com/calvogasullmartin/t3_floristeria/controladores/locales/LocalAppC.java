package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;


public abstract class LocalAppC extends LocalC implements AppC{

    public LocalAppC(Manager manager) {
        super(manager);
    }    
        
    @Override
    public abstract void aceptar(AppCVisitor controlador);             
}
