package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalAppC2;
import com.calvogasullmartin.t3_floristeria.modelos.Estado2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC2;

public abstract class LocalPersistenciaC2 extends LocalAppC2 implements PersistenciaC2{

    protected final DaoFactory2 factory;
    
    final String errorBD = "No se ha podido acceder a la base de datos";
    
    public LocalPersistenciaC2(Manager2 manager) {
        super(manager);
        this. factory = DaoFactory2.getFactory();
    }

    
    @Override
    public abstract void aceptar(AppCVisitorC2 controlador);

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado2.EN_MENU);
    }
    
    @Override
    public String getErrorBD() {
        return errorBD;
    }
    
}
