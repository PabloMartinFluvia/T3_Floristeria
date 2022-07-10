package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalAddProductoControlador extends LocalControladorPadre implements AddProductoControlador{

    public LocalAddProductoControlador(Estados estados) {
        super(estados);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
}
