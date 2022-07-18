package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.AppControlador;

public class AppVista implements Vista{       
    
    @Override
    public void interactuar(AppControlador controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    /*
    @Override
    public void visitar(MenuControlador controlador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    */
}
