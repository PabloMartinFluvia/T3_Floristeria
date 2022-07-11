package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalModificarProductoControlador extends LocalMostrarConjuntoControlador implements ModificarProductoControlador{

    private boolean isUpdateUnits; //si falso -> is delete product
    
    public LocalModificarProductoControlador(Estados estados, boolean isStock, boolean isUpdate) {
        super(estados, isStock);
        this.isUpdateUnits = this.isUpdateUnits;
    }
        
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public boolean IsUpdateUnits() {
        return isUpdateUnits;
    }
        
}
