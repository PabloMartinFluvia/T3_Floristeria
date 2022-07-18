package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public abstract class LocalControlador {
    
    private Manager manager;

    public LocalControlador(Manager manager) {
        this.manager = manager;
    }
                
    public void setEstado(Estado estado){
	assert estado != null;
	this.manager.setEstado(estado);
    }   
    
    public Estado getEstado(){
        return this.manager.getEstado();
    }
    
    public int getNUM_CATEGORIAS() {
        return this.manager.getNUM_CATEGORIAS();
    }

    public String getErrorBD() {
        return this.manager.getErrorBD();
    }       

    public int getMAX_UNIDADES_EN_STOCK() {
        return this.manager.getMAX_UNIDADES_EN_STOCK();
    }
}
