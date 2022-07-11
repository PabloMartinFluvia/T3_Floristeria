package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;

public class AppVista implements Vista{

    //atributos: las vistas que interactuaran con cada controlador que llegue
    private BienvenidaVista bienvenidaVista;
    
    private MenuPrincipalVista menuVista;
    
    private ConjuntoProductosVista menuStock;

    public AppVista() {
        //instanciar los atributos (hacer el new). Sus constructores no tienen parametors
        bienvenidaVista = new BienvenidaVista();
        menuVista = new MenuPrincipalVista();
        menuStock = new ConjuntoProductosVista();
    }
    
    @Override
    public void interactuar(ControladorPadre controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(ArrancarAppControlador controlador) {
        assert controlador != null;
        bienvenidaVista.interactuar(controlador);
    }
    
    @Override
    public void visitar(MenuControlador controlador) {
       assert controlador != null;
       menuVista.interactuar(controlador);
    }
    
    @Override
    public void visitar(AddProductoControlador controlador) {
       assert controlador != null;
       menuStock.interactuar(controlador);       
    }

    @Override
    public void visitar(MostrarConjuntoControlador controlador) {
       assert controlador != null;
       menuStock.interactuar(controlador);
    }
    
    
}
