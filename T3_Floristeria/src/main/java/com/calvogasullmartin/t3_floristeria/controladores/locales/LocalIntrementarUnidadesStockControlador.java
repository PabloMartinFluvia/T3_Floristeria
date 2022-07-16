package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public class LocalIntrementarUnidadesStockControlador extends LocalMostrarStocksControlador implements IncrementarUnidadesStockControlador{

    public LocalIntrementarUnidadesStockControlador(Manager manager) {
        super(manager);
    }

    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
}
