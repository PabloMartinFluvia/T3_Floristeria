package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;


public interface ControladorPadre {

    final DaoFactory factory = DaoFactory.getFactory();
    
    void aceptar(ControladorPadreVisitor controlador);
    
    /*
    protected Estado estado;

    protected ControladorPadre(Estado estado) {
        this.estado = estado;
    }

    public abstract void controlar();

    protected Estado getEstado() {
        return estado;
    }

    protected void setEstado(Estado estado) {
        assert estado != null;
        this.estado = estado;
    }
    */
}
