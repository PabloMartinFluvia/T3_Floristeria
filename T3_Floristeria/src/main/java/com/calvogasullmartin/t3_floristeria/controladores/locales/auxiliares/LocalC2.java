package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Estado2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;

public abstract class LocalC2 {
    
    private Manager2 manager;
        
    public LocalC2(Manager2 manager) {
        this.manager = manager;        
    }
                
    public void setEstado(Estado2 estado){
	assert estado != null;
	this.manager.setEstado(estado);
    }   
    
    public Estado2 getEstado(){
        return this.manager.getEstado();
    }
    
    public int getNUM_CATEGORIAS() {
        return this.manager.getNUM_CATEGORIAS();
    }           

    public int getMAX_UNIDADES_EN_STOCK() {
        return this.manager.getMAX_UNIDADES_EN_STOCK();
    }

    public Manager2 getManager() {
        return manager;
    }
}
