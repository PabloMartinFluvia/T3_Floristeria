package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalAppC;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;

public abstract class LocalPersistenciaC extends LocalAppC implements PersistenciaC{

    protected final DaoFactory factory;
    
    final String errorBD = "No se ha podido acceder a la base de datos";
    
    public LocalPersistenciaC(Manager manager) {
        super(manager);
        this. factory = DaoFactory.getFactory();
    }

    
    @Override
    public abstract void aceptar(AppCVisitorC controlador);

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }
    
    @Override
    public String getErrorBD() {
        return errorBD;
    }
    
}
