package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;

public abstract class LocalPersistenciaControlador extends LocalAppControlador implements PersistenciaControlador{

    private final String errorBD;
    
    public LocalPersistenciaControlador(Manager manager) {
        super(manager);
        errorBD = manager.getErrorBD();
    }
    
    @Override
    public abstract void aceptar(AppControladorVisitor visitor);     
    
    @Override
    public String getErrorBD(){
        return errorBD;
    }
    
    @Override
    public void seleccionarMenu(){
        this.setEstado(Estado.EN_MENU);
    }
    
}
