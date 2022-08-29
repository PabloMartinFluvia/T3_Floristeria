package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.*;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.util.HashMap;

public class LocalLogica implements Logica{    
        
    private final Manager manager; 
    
    private final HashMap<Estado,Controlador> controladores;
    
    public LocalLogica() {        
        manager = new Manager();            
        controladores = new HashMap<>();
        asociarEstadosControladores();
    }
    
    @Override
    public Controlador getControlador(){             
        return controladores.get(manager.getEstado());               
    }
    
    private void asociarEstadosControladores(){  
        asociarEstadosBasicos();
        asociarEstadosMostrarValores();
        asociarEstadosMostrarListados();
        asociarEstadosModificarStock();
    }
    
    private void asociarEstadosBasicos(){
        StartC start = new LocalStartC(manager);
        controladores.put(Estado.INITIAL, start);
        MenuC menu = new LocalMenuC(manager);
        controladores.put(Estado.EN_MENU, menu);
        controladores.put(Estado.EXIT, null);
    }
    
    private void asociarEstadosMostrarValores(){
        ShowValorC valor = new LocalShowValorC(manager);
        controladores.put(Estado.MOSTRAR_VALOR_TIENDA, valor);
        ShowFacturacionC facturacion = new LocalShowFacturacionC(manager);
        controladores.put(Estado.MOSTRAR_FACTURACION_TOTAL, facturacion);
    }
    
    private void asociarEstadosMostrarListados(){
        ShowTicketsC tiquets = new LocalShowTicketsC(manager);
        controladores.put(Estado.MOSTRAR_TIQUETS, tiquets);
        ShowStocksC stocks = new LocalShowStocksC(manager);
        controladores.put(Estado.MOSTRAR_STOCK, stocks);
    }
    
    private void asociarEstadosModificarStock(){
        NuevoProductoC nuevo = new LocalNuevoProductoC(manager);
        controladores.put(Estado.NUEVO_PRODUCTO, nuevo);       
        ModificarUnidadesC modificador = new LocalModificarUnidadesC(manager);
        controladores.put(Estado.ACTUALIZAR_UNIDADES, modificador);
        RealizarVentaC venta = new LocalRealizarVentaC(manager);
        controladores.put(Estado.REALIZAR_VENTA, venta);
        DescatalogarProductoC eliminar = new LocalDescatalogarProductoC(manager);
        controladores.put(Estado.RETIRAR_PRODUCTO, eliminar);
    }        
}
