package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.FloristeriaVista;
import com.calvogasullmartin.t3_floristeria.vistasConsola.Z_OLD.Z_OLD_ConjuntoProductosVista;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.MenuVista;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.InicioControlador;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.VerValorFloristeriaController;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_MostrarTotalesControlador;

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
    public void interactuar(AppControlador controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(InicioControlador controlador) {
        assert controlador != null;
        new InicioControladorVista().interactuar(controlador);        
    }
    
    @Override
    public void visitar(MenuControlador controlador) {
       assert controlador != null;
       menuVista.interactuar(controlador);
    }
    
    @Override
    public void visitar(NuevoProductoControlador controlador) {
       assert controlador != null;
       new NuevoProductoVista().interactuar(controlador);     
    }
    
    @Override
    public void visitar(MostrarStocksControlador controlador) {
        assert controlador != null;
        new MostrarStocksVista().interactuar(controlador);
    }

    @Override
    public void visitar(IncrementarUnidadesControlador controlador) {
        assert controlador != null;
        new IncrementarUnidadesVista().interacturar(controlador);
    }
    
    @Override
    public void visitar(VerValorFloristeriaController controlador) {
        new VerValorFloristeriaVista().interactuar(controlador);
    }
    
    
    
    
    @Override
    public void visitar(Z_OLD_MostrarConjuntoControlador old_controlador) {
       assert old_controlador != null;
       conjuntoProductosVista.interactuar(old_controlador);
    }

    @Override
    public void visitar(Z_OLD_MostrarTotalesControlador controlador) {
        assert controlador != null;
        //floristeriaVista.interactuar(controlador);
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
