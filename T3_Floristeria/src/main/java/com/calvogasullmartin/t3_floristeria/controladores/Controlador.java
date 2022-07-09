package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;

public abstract class Controlador {
    
    private Estado estado;

    protected Controlador(Estado estado) {
        assert estado != null;
        this.estado = estado;
    }
    
    protected Estado getEstado(){
	return estado;
    }
	
    public void setEstado(Estado estado){
	assert estado != null;
	this.estado = estado;
    }
}
