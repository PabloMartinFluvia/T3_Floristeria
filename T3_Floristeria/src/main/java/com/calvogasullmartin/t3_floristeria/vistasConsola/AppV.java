package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.controladores.TiquetsC;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;

public class AppV implements Vista{       
    
    @Override
    public void interactuar(AppC controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(StartC controlador) {
        assert controlador != null;        
        new StartV(controlador).interactuar();
    }

    @Override
    public void visitar(MenuC controlador) {
        assert controlador != null; 
        new MenuV(controlador).interactuar();
    }

    @Override
    public void visitar(ValorC controlador) {
        assert controlador != null; 
        new ValorV().interactuar(controlador);
    }

    @Override
    public void visitar(FacturacionC controlador) {
        assert controlador != null; 
        new FacturacionV().interactuar(controlador);
    }

    @Override
    public void visitar(NuevoC controlador) {
        assert controlador != null; 
        new NuevoV(controlador).interactuar();
    }

    @Override
    public void visitar(TiquetsC controlador) {
        assert controlador != null; 
        new TiquetsV(controlador).interactuar();
    }

    
}
