package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;

public class LocalLogica implements Logica{    
        
    private Manager manager; 
    
    private Map<Estado,AppC> controladores;
    
    public LocalLogica() {        
        manager = new Manager();            
        controladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){  
        StartC start = new LocalStartC(manager);
        controladores.put(Estado.INITIAL, start);
        controladores.put(Estado.EXIT, null);
    }
        
    @Override
    public AppC getControladorPadre(){          
        return controladores.get(manager.getEstado());               
    }
}
