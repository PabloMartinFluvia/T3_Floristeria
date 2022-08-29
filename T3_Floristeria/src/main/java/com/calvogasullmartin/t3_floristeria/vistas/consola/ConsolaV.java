package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.Controlador;
import com.calvogasullmartin.t3_floristeria.controladores.DescatalogarProductoC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarUnidadesC;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoC;
import com.calvogasullmartin.t3_floristeria.controladores.RealizarVentaC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowFacturacionC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowStocksC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowTicketsC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowValorC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;

public class ConsolaV implements Vista{      
    
    @Override
    public void interact(Controlador controlador) {
        assert controlador != null;
        controlador.accept(this);
    }
    
    @Override
    public void visit(StartC controlador) {
        assert controlador != null;        
        new StartV(controlador).interact();
    }

    @Override
    public void visit(MenuC controlador) {
        assert controlador != null; 
        new MenuV(controlador).interact();
    }

    @Override
    public void visit(ShowValorC controlador) {
        assert controlador != null; 
        new ShowTotalesV().interact(controlador);
    }

    @Override
    public void visit(ShowFacturacionC controlador) {
        assert controlador != null; 
        new ShowTotalesV().interact(controlador);        
    }

    @Override
    public void visit(ShowTicketsC controlador) {
        assert controlador != null; 
        new ShowConjuntosV().interact(controlador);
    }

    @Override
    public void visit(ShowStocksC controlador) {
        assert controlador != null; 
        new ShowConjuntosV().interact(controlador);
    }
        
    @Override
    public void visit(NuevoProductoC controlador) {
        assert controlador != null; 
        new NuevoProductoV(controlador).interact();
    }

    @Override
    public void visit(ModificarUnidadesC controlador) {
        assert controlador != null;
        new ModificarUnidadesV(controlador).interact();
    }

    @Override
    public void visit(RealizarVentaC controlador) {
        assert controlador != null;
        new RealizarVentaV(controlador).interact();
    }

    @Override
    public void visit(DescatalogarProductoC controlador) {
        assert controlador != null;
        new DescataogarProductoV(controlador).interact();
    }    
}
