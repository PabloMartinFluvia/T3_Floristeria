package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;

public class LocalMostrarStocksControlador extends LocalStocksController implements MostrarStocksControlador{           
            
    public LocalMostrarStocksControlador(Manager manager) {
        super(manager);                
    }

    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public void setConUnidades(boolean conUnidades) {
        this.conUnidades = conUnidades;
    }
    
}
