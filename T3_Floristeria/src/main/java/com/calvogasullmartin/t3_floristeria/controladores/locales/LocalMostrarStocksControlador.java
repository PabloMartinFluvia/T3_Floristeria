package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalStocksController;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;

public class LocalMostrarStocksControlador extends LocalStocksController implements MostrarStocksControlador{           
            
    public LocalMostrarStocksControlador(Manager manager) {
        super(manager);                
    }

    @Override
    public void aceptar(AppControladorVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public void setConUnidades(boolean conUnidades) {
        this.conUnidades = conUnidades;
    }
    
}
