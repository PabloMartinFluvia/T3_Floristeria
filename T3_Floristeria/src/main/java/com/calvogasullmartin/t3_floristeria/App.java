package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.AppC;

public class App {

    private Logica logica;

    private Vista vistaPrincipal;

    public App() {
        Configurador configurador = new Configurador();
        configurarFamiliaPersistencia(configurador);
        inyectarDependencias(configurador);
    }
    
    private void configurarFamiliaPersistencia(Configurador configurador){
        configurador.persistenciaTxt();
    }

    private void inyectarDependencias(Configurador configurador) {                
        this.vistaPrincipal = configurador.vistasPorConsola();
        this.logica = configurador.controladoresLocales();           
    }

    public static void main(String[] args) {        
        new App().ejecutar();
    }
    
    private void ejecutar() {
        
        AppC controlador;        
        do{           
            controlador = logica.getControladorPadre();
            if (controlador != null){
                vistaPrincipal.interactuar(controlador);
            }            
        }while(controlador != null);        
    }   
}
