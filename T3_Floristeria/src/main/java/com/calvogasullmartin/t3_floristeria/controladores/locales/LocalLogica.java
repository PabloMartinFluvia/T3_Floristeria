package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;

public class LocalLogica implements Logica{    
    //atributos:
    //A) las entidades del modelo (contienen info del
    //B) els controladors concrets (familia segun que familia concreta de controladores pertenezca esta Logia)
    
    private Aplicacion aplicacion; //floristeria, y otros modelos
    
    private Estados estados;
        
    // los controladores se asocian a los modelos con los que "trabajan"
    private ArrancarAppControlador arrancarAppControlador; 
    
    private MenuControlador menuControlador;
    
    private AddProductoControlador addProductoControlador;
    //añadir más a medida que se amplie la funcionalidad
    
    private Map<Estado,ControladorPadre> mapaEstadosContoladores;            

    public LocalLogica() {
        aplicacion = new Aplicacion(); 
        estados = new Estados(Estado.INITIAL);        
        this.arrancarAppControlador = new LocalArrancarAppControlador(estados, aplicacion.getFloristeria());
        this.menuControlador = new LocalMenuControlador(estados);
        this.addProductoControlador = new LocalAddProductoControlador(estados);
        //i mas
        mapaEstadosContoladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){        
        mapaEstadosContoladores.put(Estado.INITIAL, arrancarAppControlador);  
        mapaEstadosContoladores.put(Estado.EN_MENU, menuControlador); 
        mapaEstadosContoladores.put(Estado.NUEVO_PRODUCTO, addProductoControlador);
        //añadir más a medida que se amplie la funcionalidad
        mapaEstadosContoladores.put(Estado.EXIT, null);
    }
        
    @Override
    public ControladorPadre getControladorPadre(){  
        //System.out.println(estado);
        return mapaEstadosContoladores.get(estados.getEstado());               
    }
}
