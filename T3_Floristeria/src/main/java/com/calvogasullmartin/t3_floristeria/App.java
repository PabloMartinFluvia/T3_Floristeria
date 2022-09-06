package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.vistas.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.Controlador;

public class App {

    private Logica logica;

    private Vista vista;
    
    private App() {
        configurarApp();
    }
    
    private void configurarApp(){
        Configurador configurador = new Configurador();
        //configurador.persistenciaTxt(); // persistencia en archivo txt
        //configurador.persistenciaMySql(); // persistencia en BD MySql
        configurador.persistenciaMongoDB(); // persistencia en BD Mongo
        this.vista = configurador.vistasConsola(); //interacción con usuario es por consola
        this.logica = configurador.controladoresLocales(); // los controladores son locales
    }

    public static void main(String[] args) {
        new App().ejecutar(); 
    }
    
    private void ejecutar() {        
        Controlador controlador;        
        do{           
            controlador = logica.getControlador();
            if (controlador != null){
                vista.interact(controlador);
            }            
        }while(controlador != null);        
    }   
}
