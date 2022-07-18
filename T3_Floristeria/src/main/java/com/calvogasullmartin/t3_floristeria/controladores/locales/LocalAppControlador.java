package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.controladores.AppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;


public abstract class LocalAppControlador implements AppControlador{
    
    private Manager manager;
    
    public LocalAppControlador(Manager manager) {
        assert manager != null;
        this.manager = manager;
    }

    @Override
    public abstract void aceptar(AppControladorVisitor controlador);
    
    @Override
    public void setEstado(Estado estado){
	assert estado != null;
	this.manager.setEstado(estado);
    }        
}
