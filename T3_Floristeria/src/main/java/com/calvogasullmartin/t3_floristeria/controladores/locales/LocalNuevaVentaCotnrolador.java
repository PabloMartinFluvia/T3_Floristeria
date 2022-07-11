package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalNuevaVentaCotnrolador extends LocalControladorPadre implements NuevaVentaControlador{

    public LocalNuevaVentaCotnrolador(Estados estados) {
        super(estados);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
}
