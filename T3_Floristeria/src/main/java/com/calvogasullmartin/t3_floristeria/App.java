package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.config.Configurador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.vistas.AppVista;


public class App {
    
    private LogicaInterface logica;
    
    private VistaInterface vistaPrincipal;        
    
    public App (LogicaInterface logica, VistaInterface vistaPrincipal){
        configurar();    
        this.logica = logica;
        this.vistaPrincipal = vistaPrincipal;
    }            
    
    private void configurar(){
        new Configurador().configurarDaoFactory();         
    }
    
    private void ejecutar(){
        ControladorFuncional controlador;        
        do{
            controlador = logica.getControladorFuncional();
            if (controlador != null){
                vistaPrincipal.interactua(controlador);
            }            
        }while(controlador != null);
    } 
    
    public static void main(String[] args) {        
        new App(new Logica(), new AppVista()).ejecutar();
    }
}