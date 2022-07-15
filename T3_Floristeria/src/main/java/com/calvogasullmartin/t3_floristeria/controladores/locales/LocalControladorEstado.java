package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public abstract class LocalControladorEstado {
    
    private Manager estados;

    protected LocalControladorEstado(Manager estados) {
        assert estados != null;
        this.estados = estados;
    }
    
    protected Estado getEstado(){
	return estados.getEstado();
    }
	
    public void setEstado(Estado estado){
	assert estado != null;
	this.estados.setEstado(estado);
    }
}
