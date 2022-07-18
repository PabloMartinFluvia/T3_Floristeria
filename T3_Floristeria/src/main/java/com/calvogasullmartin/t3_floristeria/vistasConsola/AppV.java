package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;

public class AppV implements Vista{       
    
    @Override
    public void interactuar(AppC controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(StartC controlador) {
        assert controlador != null;        
        new StartV().interactuar(controlador);
    }

    @Override
    public void visitar(MenuC controlador) {
        assert controlador != null; 
        new MenuV().interactuar(controlador);
    }

    
}
