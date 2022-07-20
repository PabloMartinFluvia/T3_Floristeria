package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC;

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
        MenuC menu = new LocalMenuC(manager);
        controladores.put(Estado.EN_MENU, menu);
        ValorC valor = new LocalValorC(manager);
        controladores.put(Estado.MOSTRAR_VALOR_TIENDA, valor);
        FacturacionC facturacion = new LocalFacturacionC(manager);
        controladores.put(Estado.MOSTRAR_FACTURACION_TOTAL, facturacion);
        NuevoC nuevo = new LocalNuevoC(manager);
        controladores.put(Estado.NUEVO_PRODUCTO, nuevo);
        TicketsC tiquets = new LocalTicketsC(manager);
        controladores.put(Estado.MOSTRAR_TIQUETS, tiquets);
        StocksC stocks = new LocalStocksC(manager);
        controladores.put(Estado.MOSTRAR_STOCK, stocks);
        ModificadorC modificador = new LocalModificadorC(manager);
        controladores.put(Estado.ACTUALIZAR_UNIDADES, modificador);
        controladores.put(Estado.EXIT, null);
    }
        
    @Override
    public AppC getControladorPadre(){          
        return controladores.get(manager.getEstado());               
    }
}
