package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.EliminarC2;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC2;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC2;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC2;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC2;
import com.calvogasullmartin.t3_floristeria.controladores.StartC2;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC2;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC2;
import com.calvogasullmartin.t3_floristeria.controladores.VentaC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC2;

public class AppV2 implements Vista{       
    
    @Override
    public void interactuar(AppC2 controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(StartC2 controlador) {
        assert controlador != null;        
        new StartV2(controlador).interactuar();
    }

    @Override
    public void visitar(MenuC2 controlador) {
        assert controlador != null; 
        new MenuV2(controlador).interactuar();
    }

    @Override
    public void visitar(ValorC2 controlador) {
        assert controlador != null; 
        new ValorV2().interactuar(controlador);
    }

    @Override
    public void visitar(FacturacionC2 controlador) {
        assert controlador != null; 
        new FacturacionV2().interactuar(controlador);
    }

    @Override
    public void visitar(NuevoC2 controlador) {
        assert controlador != null; 
        new NuevoV2(controlador).interactuar();
    }

    @Override
    public void visitar(TicketsC2 controlador) {
        assert controlador != null; 
        new TiquetsV2().interactuar(controlador);
    }

    @Override
    public void visitar(StocksC2 controlador) {
        assert controlador != null; 
        new StocksV2().interactuar(controlador);
    }

    @Override
    public void visitar(ModificadorC2 controlador) {
        assert controlador != null;
        new ModificadorV2(controlador).interactuar();
    }

    @Override
    public void visistar(VentaC2 controlador) {
        assert controlador != null;
        new VentaV2(controlador).interactuar();
    }

    @Override
    public void visistar(EliminarC2 controlador) {
        assert controlador != null;
        new EliminarV2(controlador).interactuar();
    }

    
}
