package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.AppControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.util.HashMap;
import java.util.Map;

public class LocalLogica implements Logica{    
        
    private Manager manager; 
    
    private Map<Estado,AppControlador> mapaEstadosContoladores;
    
    public LocalLogica() {        
        manager = new Manager();            
        mapaEstadosContoladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){                
        mapaEstadosContoladores.put(Estado.EXIT, null);
    }
        
    @Override
    public AppControlador getControladorPadre(){          
        return mapaEstadosContoladores.get(manager.getEstado());               
    }
}
