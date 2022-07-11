package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalMostrarConjuntoControlador extends LocalControladorPadre implements MostrarConjuntoControlador{

    public LocalMostrarConjuntoControlador(Estados estados) {
        super(estados);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }    
}
