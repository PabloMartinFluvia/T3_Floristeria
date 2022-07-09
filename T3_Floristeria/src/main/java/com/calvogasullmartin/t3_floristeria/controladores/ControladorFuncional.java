package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;


public interface ControladorFuncional {

    final DaoFactory factory = DaoFactory.getFactory();
    
    void aceptar(ControladorFuncionalVisitor controlador);
    
    /*
    protected Estado estado;

    protected ControladorFuncional(Estado estado) {
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
