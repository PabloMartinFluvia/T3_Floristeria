package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.config.Configurador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.vistas.AppVista;

public class App {
    
    private Logica logica;
    private Estado estado;
    private AppVista vistaPrincipal;
    
    private App (){
        configurar();
        estado = Estado.INITIAL;
        logica = new Logica(estado); 
        vistaPrincipal = new AppVista();
    }
    
    public static void main(String[] args) {
        new App().ejecutar();
    }    
    
    /*
    Inicializa la configuración inicial.
    Que base de datos se usa, que familia de vistas, que famila de controladores, etc...    
    */
    private void configurar(){
        new Configurador().configurar();         
    }
    
    /*
    Arranca la aplicación
    */
    private void ejecutar(){
        ControladorFuncional controlador;        
        do{
            controlador = logica.getControlador();
            if (controlador != null){
                vistaPrincipal.interactua(controlador);
            }            
        }while(controlador != null);
        // vistaPrincipal.despidete;
    }    
}