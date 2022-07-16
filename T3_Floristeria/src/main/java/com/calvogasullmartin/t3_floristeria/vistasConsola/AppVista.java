package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD_MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD_ModificarProductoControlador;

public class AppVista implements Vista{       
    
    private MenuVista menuVista;
    
    
    
    private Z_OLD_ConjuntoProductosVista conjuntoProductosVista;
    
    private FloristeriaVista floristeriaVista;

    public AppVista() {
        menuVista = new MenuVista();
        
        conjuntoProductosVista = new Z_OLD_ConjuntoProductosVista();
        floristeriaVista = new FloristeriaVista();
    }
    
    @Override
    public void interactuar(ControladorPadre controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(ArrancarAppControlador controlador) {
        assert controlador != null;
        new ArrancarVista().interactuar(controlador);        
    }
    
    @Override
    public void visitar(MenuControlador controlador) {
       assert controlador != null;
       menuVista.interactuar(controlador);
    }
    
    @Override
    public void visitar(AddProductoControlador controlador) {
       assert controlador != null;
       new AddVista().interactuar(controlador);     
    }
    
    @Override
    public void visitar(MostrarStocksControlador controlador) {
        assert controlador != null;
        new MostrarStocksVista().interactuar(controlador);
    }

    @Override
    public void visitar(IncrementarUnidadesStockControlador controlador) {
        assert controlador != null;
        new IncrementarUnidadesVista().interacturar(controlador);
    }
    
    
    
    @Override
    public void visitar(Z_OLD_MostrarConjuntoControlador old_controlador) {
       assert old_controlador != null;
       conjuntoProductosVista.interactuar(old_controlador);
    }

    @Override
    public void visitar(MostrarTotalesControlador controlador) {
        assert controlador != null;
        floristeriaVista.interactuar(controlador);
    }

    @Override
    public void visitar(Z_OLD_ModificarProductoControlador controlador) {
        assert controlador != null;
        conjuntoProductosVista.interactuar(controlador);
    }

    @Override
    public void visitar(NuevaVentaControlador controlador) {
       assert controlador != null;
       conjuntoProductosVista.interactuar(controlador);
    }

    

    
    
    
}
