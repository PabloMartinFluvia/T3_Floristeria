package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC;
import com.calvogasullmartin.t3_floristeria.controladores.VentaC;

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
    public void visitar(TicketsC controlador) {
        assert controlador != null; 
        new TiquetsV().interactuar(controlador);
    }

    @Override
    public void visitar(StocksC controlador) {
        assert controlador != null; 
        new StocksV().interactuar(controlador);
    }

    @Override
    public void visitar(ModificadorC controlador) {
        assert controlador != null;
        new ModificadorV(controlador).interactuar();
    }

    @Override
    public void visistar(VentaC controlador) {
        assert controlador != null;
        new VentaV(controlador).interactuar();
    }

    
}
