package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.PersistenciaC;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;

public abstract class LocalPersistenciaC extends LocalControladorManager implements PersistenciaC{

    protected String errorBD;
    
    protected DaoFactory factory;
    
    public LocalPersistenciaC(Manager manager) {
        super(manager);
        factory = DaoFactory.getFactory();
    }
    
    @Override
    public String getErrorBD() {
        return errorBD;
    }

    @Override
    public void selectMenu() {
        this.setEstate(Estado.EN_MENU);
    }

    @Override
    public void selectExit() {
       this.setEstate(Estado.EXIT);
    }
}
