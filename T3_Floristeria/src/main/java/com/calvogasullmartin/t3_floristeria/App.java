package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.vistas.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.Controlador;


/*
VISTAS conocen solo interfaces de los controladores.
    En algún caso pueden conocer enums de los modelos (ya que son constantes)
CONTROLADORES solo conocen los modelos y las interfaces / abstracciones del patrón DAO
MODELOS solo conocen modelos
*/
public class App {

    private final Logica logica;

    private final Vista vista;

    /**
     * El configurador inyecta las dependencias.
     * 1) Familia de persistencia
     * 2) Tipo de vistas
     * 3) Tipo de controladores
     */
    private App() {
        Configurador configurador = new Configurador();
        configurador.persistenciaTxt();
        this.vista = configurador.vistasConsola();
        this.logica = configurador.controladoresLocales();
    }

    public static void main(String[] args) {
        new App().ejecutar(); 
    }
    
    /**
     * Se pregunta a la lógica "que controlador le toca".
     * Se le dice a la vista que trabaje con "ese controloador".
     */
    private void ejecutar() {        
        Controlador controlador;        
        do{           
            controlador = logica.getControladorFuncional();
            if (controlador != null){
                vista.interact(controlador);
            }            
        }while(controlador != null);        
    }   
}
