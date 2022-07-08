package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;


public abstract class ControladorFuncional {

    protected final DaoFactory factory = DaoFactory.getFactory();

    protected Estado estado;

    protected ControladorFuncional(Estado estado) {
        this.estado = estado;
    }

    protected Estado getEstado() {
        return estado;
    }

    protected void setEstado(Estado estado) {
        assert estado != null;
        this.estado = estado;
    }
}
