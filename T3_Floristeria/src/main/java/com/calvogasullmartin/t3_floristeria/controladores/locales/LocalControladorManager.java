package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public abstract class LocalControladorManager {
    
    private Manager manager;

    protected LocalControladorManager(Manager manager) {
        assert manager != null;
        this.manager = manager;
    }
    
    protected Estado getEstado(){
	return manager.getEstado();
    }
	
    public void setEstado(Estado estado){
	assert estado != null;
	this.manager.setEstado(estado);
    }
    
    public String getErrorBD(){
        return manager.getErrorBD();
    }
    
}
