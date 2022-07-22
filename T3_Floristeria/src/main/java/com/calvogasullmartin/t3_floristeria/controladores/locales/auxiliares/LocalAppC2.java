package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC2;


public abstract class LocalAppC2 extends LocalC2 implements AppC2{

    public LocalAppC2(Manager2 manager) {
        super(manager);
    }    
        
    @Override
    public abstract void aceptar(AppCVisitorC2 controlador);             
}
