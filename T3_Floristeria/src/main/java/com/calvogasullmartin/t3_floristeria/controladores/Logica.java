package com.calvogasullmartin.t3_floristeria.controladores;


import com.calvogasullmartin.t3_floristeria.LogicaInterface;
import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import java.util.HashMap;
import java.util.Map;

public class Logica implements LogicaInterface{    
    //atributos:
    //A) las entidades del modelo (contienen info del
    //B) els controladors concrets (familia segun que familia concreta de controladores pertenezca esta Logia)
    
    private Aplicacion aplicacion; //contiene estado, floristeria, y otros modelos
        
    // los controladores se asocian a los modelos con los que "trabajan"
    private ArrancarAppControlador arrancarAppControlador; 
    //añadir más a medida que se amplie la funcionalidad
    private Map<Estado,ControladorFuncional> controladores;            

    public Logica() {
        aplicacion = new Aplicacion(); // la aplicacion pone el estado = INICIAL
        this.arrancarAppControlador = new ArrancarAppControlador(
                aplicacion.getEstado(), aplicacion.getFloristeria());
        
        controladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){        
        controladores.put(Estado.INITIAL, arrancarAppControlador);  
        //añadir más a medida que se amplie la funcionalidad
        controladores.put(Estado.EXIT, null);
    }
        
    @Override
    public ControladorFuncional getControladorFuncional(){
            return controladores.get(aplicacion.getEstado());        
    }
}
