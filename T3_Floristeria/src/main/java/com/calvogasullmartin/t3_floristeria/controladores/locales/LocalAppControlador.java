package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.AppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;


public abstract class LocalAppControlador extends LocalControlador implements AppControlador{

    public LocalAppControlador(Manager manager) {
        super(manager);
    }    
        
    @Override
    public abstract void aceptar(AppControladorVisitor controlador);             
}
