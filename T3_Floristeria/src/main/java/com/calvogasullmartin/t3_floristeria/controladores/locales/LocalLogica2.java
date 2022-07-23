package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.*;
import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC2;

public class LocalLogica2 implements Logica{    
        
    private Manager2 manager; 
    
    private Map<Estado2,AppC2> controladores;
    
    public LocalLogica2() {        
        manager = new Manager2();            
        controladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){  
        StartC start = new LocalStartC2(manager);
        controladores.put(Estado2.INITIAL, start);
        MenuC menu = new LocalMenuC2(manager);
        controladores.put(Estado2.EN_MENU, menu);
        ValorC valor = new LocalValorC2(manager);
        controladores.put(Estado2.MOSTRAR_VALOR_TIENDA, valor);
        FacturacionC facturacion = new LocalFacturacionC2(manager);
        controladores.put(Estado2.MOSTRAR_FACTURACION_TOTAL, facturacion);
        NuevoC2 nuevo = new LocalNuevoC2(manager);
        controladores.put(Estado2.NUEVO_PRODUCTO, nuevo);
        TicketsC2 tiquets = new LocalTicketsC2(manager);
        controladores.put(Estado2.MOSTRAR_TIQUETS, tiquets);
        StocksC2 stocks = new LocalStocksC2(manager);
        controladores.put(Estado2.MOSTRAR_STOCK, stocks);
        ModificadorC2 modificador = new LocalModificadorC2(manager);
        controladores.put(Estado2.ACTUALIZAR_UNIDADES, modificador);
        VentaC2 venta = new LocalVentaC2(manager);
        controladores.put(Estado2.REALIZAR_VENTA, venta);
        EliminarC2 eliminar = new LocalEliminarC2(manager);
        controladores.put(Estado2.RETIRAR_PRODUCTO, eliminar);
        controladores.put(Estado2.EXIT, null);
    }
        
    @Override
    public AppC2 getControladorPadre(){          
        return controladores.get(manager.getEstado());               
    }
}
