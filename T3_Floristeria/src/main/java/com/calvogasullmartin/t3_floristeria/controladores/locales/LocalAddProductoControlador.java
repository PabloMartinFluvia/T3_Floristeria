package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;

public class LocalAddProductoControlador extends LocalControladorPadre implements AddProductoControlador{

    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
}
