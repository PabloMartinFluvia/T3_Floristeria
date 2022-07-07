package com.calvogasullmartin.t3_floristeria.controladores;


import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.util.HashMap;
import java.util.Map;

public class Logica {    
    //atributos:
    //A) las entidades del modelo (contienen info del
    //B) els controladors 
    
    private Estado estado;
    private Floristeria floristeria;
    
    // los controladores se asocian a los modelos con los que "trabajan"
    private ArrancarAppControlador arrancarAppControlador; 
    private Map<Estado,ControladorFuncional> controladores;
    

    public Logica(Estado estado) {
        this.estado = estado;                
        floristeria = new Floristeria();//atributs sense inicialitzar
        this.arrancarAppControlador = new ArrancarAppControlador(estado, floristeria);
        
        controladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){
        controladores.put(Estado.INITIAL, arrancarAppControlador);        
    }
    
    // El enum Estado es quien guarda "en que situación se encuentra la App"
    // Segun la situación se usará un controlador X o Y o....
    // Cuando la "tarea" a realizar se completa, entonces el controlador canvia el valor del Estado.
    public ControladorFuncional getControlador(){
        if (estado != Estado.EXIT){
            return controladores.get(estado);
        }else {
            return null;
        }
    }
}
