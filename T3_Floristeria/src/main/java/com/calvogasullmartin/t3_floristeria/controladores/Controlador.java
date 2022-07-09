package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;

public abstract class Controlador {
    
    private Aplicacion aplicacion;

    protected Controlador(Aplicacion aplicacion) {
        assert aplicacion != null;
        this.aplicacion = aplicacion;
    }
    
    protected Estado getEstado(){
	return aplicacion.getEstado();
    }
	
    public void setEstado(Estado estado){
	assert estado != null;
	aplicacion.setEstado(estado);
    }
}
