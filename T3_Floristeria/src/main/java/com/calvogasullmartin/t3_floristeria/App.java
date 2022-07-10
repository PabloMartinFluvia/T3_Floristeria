package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;

public class App {

    private Logica logica;

    private Vista vistaPrincipal;

    public App() {
        configurarDependencias();
    }

    private void configurarDependencias() {
        Configurador configurador = new Configurador();        
        this.vistaPrincipal = configurador.vistasPorConsola();
        this.logica = configurador.controladoresLocales();
        configurador.persistenciaTxt();    
        //i más metodos según capa de persistencia
    }

    public static void main(String[] args) {
        new App().ejecutar();
    }
    
    private void ejecutar() {
        
        ControladorPadre controlador;        
        do{            
            controlador = logica.getControladorPadre();
            if (controlador != null){
                vistaPrincipal.interactuar(controlador);
            }            
        }while(controlador != null);
         
        /*
        ControladorPadre controlador;
        controlador = logica.getControladorPadre();
        if (controlador != null) {
            vistaPrincipal.interactuar(controlador);
        }        
        controlador = logica.getControladorPadre();
        */
    }

    
}
