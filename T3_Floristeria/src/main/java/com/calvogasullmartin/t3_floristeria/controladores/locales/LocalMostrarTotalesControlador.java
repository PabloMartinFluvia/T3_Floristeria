package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalMostrarTotalesControlador extends LocalControladorPadre implements MostrarTotalesControlador{

    public LocalMostrarTotalesControlador(Estados estados) {
        super(estados);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
}
