package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;

public class LocalLogica implements Logica{    
    
    // clase que proporciona a todos los controladores informacion de como
    // se gestiona la aplicacion
    private Manager manager; 

    private ArrancarAppControlador arrancarAppControlador;     
    
    private MenuControlador menuControlador;
    
    private AddProductoControlador addProductoControlador;
    
    
    
    private MostrarConjuntoControlador mostrarStockControlador;
    
    private MostrarConjuntoControlador mostrarTicketsControlador;
    
    private MostrarTotalesControlador mostrarTotalValorControlador;
    
    private MostrarTotalesControlador mostrarTotalFacturacionControlador;
    
    private ModificarProductoControlador modificarUnidadControlador;
    
    private ModificarProductoControlador eliminarProductoControlador;
    
    private NuevaVentaControlador nuevaVentaControlador;
    
    private Map<Estado,ControladorPadre> mapaEstadosContoladores;            

    public LocalLogica() {        
        manager = new Manager();    
        this.arrancarAppControlador = new LocalArrancarAppControlador(manager);
        this.menuControlador = new LocalMenuControlador(manager);
        this.addProductoControlador = new LocalAddProductoControlador(manager);
        
        this.mostrarStockControlador = new LocalMostrarConjuntoControlador(manager, true); //stocks
        this.mostrarTicketsControlador = new LocalMostrarConjuntoControlador(manager, false); //tiquets
        this.mostrarTotalValorControlador = new LocalMostrarTotalesControlador(manager, true); // totalValorStocks
        this.mostrarTotalFacturacionControlador = new LocalMostrarTotalesControlador(manager, false); //totalValorTiquets
        this.modificarUnidadControlador = new LocalModificarProductoControlador(manager, true); // actualizar unidades de un producto que está en stock
        this.eliminarProductoControlador = new LocalModificarProductoControlador(manager, false); // eliminar un producto que está en stock
        this.nuevaVentaControlador = new LocalNuevaVentaCotnrolador(manager);
        mapaEstadosContoladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){        
        mapaEstadosContoladores.put(Estado.INITIAL, arrancarAppControlador);  
        mapaEstadosContoladores.put(Estado.EN_MENU, menuControlador); 
        mapaEstadosContoladores.put(Estado.NUEVO_PRODUCTO, addProductoControlador);
        
        mapaEstadosContoladores.put(Estado.MOSTRAR_STOCK, mostrarStockControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_TIQUETS, mostrarTicketsControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_VALOR_TIENDA, mostrarTotalValorControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_FACTURACION_TOTAL, mostrarTotalFacturacionControlador);
        mapaEstadosContoladores.put(Estado.ACTUALIZAR_UNIDADES, modificarUnidadControlador);
        mapaEstadosContoladores.put(Estado.RETIRAR_PRODUCTO, eliminarProductoControlador);
        mapaEstadosContoladores.put(Estado.REALIZAR_VENTA, nuevaVentaControlador);
        mapaEstadosContoladores.put(Estado.EXIT, null);
    }
        
    @Override
    public ControladorPadre getControladorPadre(){          
        return mapaEstadosContoladores.get(manager.getEstado());               
    }
}
