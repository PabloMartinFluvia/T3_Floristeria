package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.LogicaInterface;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class Logica implements LogicaInterface{    
    //atributos:
    //A) las entidades del modelo (contienen info del
    //B) els controladors concrets (familia segun que familia concreta de controladores pertenezca esta Logia)
    
    private Aplicacion aplicacion; //contiene estado, floristeria, y otros modelos
    
    private Estados estados;
        
    // los controladores se asocian a los modelos con los que "trabajan"
    private ArrancarAppControlador arrancarAppControlador; 
    
    private MenuControlador menuControlador;
    //añadir más a medida que se amplie la funcionalidad
    private Map<Estado,ControladorFuncional> controladores;            

    public Logica() {
        aplicacion = new Aplicacion(); // la aplicacion pone el estado = INICIAL
        estados = new Estados(Estado.INITIAL);        
        this.arrancarAppControlador = new LocalArrancarAppControlador(estados, aplicacion.getFloristeria());
        this.menuControlador = new LocalMenuControlador(estados);
        //i mas
        controladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){        
        controladores.put(Estado.INITIAL, arrancarAppControlador);  
        controladores.put(Estado.EN_MENU, menuControlador); 
        //añadir más a medida que se amplie la funcionalidad
        controladores.put(Estado.EXIT, null);
    }
        
    @Override
    public ControladorFuncional getControladorFuncional(){  
        //System.out.println(estado);
        return controladores.get(estados.getEstado());               
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }
    
    
}
