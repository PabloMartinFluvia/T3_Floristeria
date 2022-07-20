package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public abstract class LocalC {
    
    private Manager manager;
        
    public LocalC(Manager manager) {
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

    public int getMAX_UNIDADES_EN_STOCK() {
        return this.manager.getMAX_UNIDADES_EN_STOCK();
    }

    public Manager getManager() {
        return manager;
    }
}
