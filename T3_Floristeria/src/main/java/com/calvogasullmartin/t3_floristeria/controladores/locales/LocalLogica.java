package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.Z_OLD.LocalMostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.locales.Z_OLD.LocalNuevaVentaCotnrolador;
import com.calvogasullmartin.t3_floristeria.controladores.locales.Z_OLD.Z_OLD_LocalModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.locales.Z_OLD.Z_OLD_LocalMostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import java.util.HashMap;
import java.util.Map;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.InicioControlador;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesControlador;

public class LocalLogica implements Logica{    
        
    private Manager manager; 

    private InicioControlador arrancarAppControlador;     
    
    private MenuControlador menuControlador;
    
    private NuevoProductoControlador addProductoControlador;
    
    private MostrarStocksControlador mostrarStocksControlador;
    
    private IncrementarUnidadesControlador incrementarUnidadesStockControlador;
    
    
    
    private Z_OLD_MostrarConjuntoControlador mostrarTicketsControlador;    
    private MostrarTotalesControlador mostrarTotalValorControlador;    
    private MostrarTotalesControlador mostrarTotalFacturacionControlador;            
    private Z_OLD_ModificarProductoControlador eliminarProductoControlador;    
    private NuevaVentaControlador nuevaVentaControlador;
    
    
    private Z_OLD_MostrarConjuntoControlador old_mostrarStockControlador;
    private Z_OLD_ModificarProductoControlador old_modificarUnidadControlador;
    
    private Map<Estado,AppControlador> mapaEstadosContoladores;            

    public LocalLogica() {        
        manager = new Manager();    
        this.arrancarAppControlador = new LocalInicioControlador(manager);
        this.menuControlador = new LocalMenuControlador(manager);
        this.addProductoControlador = new LocalNuevoProductoControlador(manager);
        this.mostrarStocksControlador = new LocalMostrarStocksControlador(manager);
        this.incrementarUnidadesStockControlador = new LocalIncrementarUnidadesControlador(manager);
        
        
        this.mostrarTicketsControlador = new Z_OLD_LocalMostrarConjuntoControlador(manager, false); //tiquets
        this.mostrarTotalValorControlador = new LocalMostrarTotalesControlador(manager, true); // totalValorStocks
        this.mostrarTotalFacturacionControlador = new LocalMostrarTotalesControlador(manager, false); //totalValorTiquets        
        this.eliminarProductoControlador = new Z_OLD_LocalModificarProductoControlador(manager, false); // eliminar un producto que está en stock
        this.nuevaVentaControlador = new LocalNuevaVentaCotnrolador(manager);
        this.old_mostrarStockControlador = new Z_OLD_LocalMostrarConjuntoControlador(manager, true); //stocks
        this.old_modificarUnidadControlador = new Z_OLD_LocalModificarProductoControlador(manager, true); // actualizar unidades de un producto que está en stock

        mapaEstadosContoladores = new HashMap<>();
        coordinarControladores();
    }
    
    private void coordinarControladores(){        
        mapaEstadosContoladores.put(Estado.INITIAL, arrancarAppControlador);  
        mapaEstadosContoladores.put(Estado.EN_MENU, menuControlador); 
        mapaEstadosContoladores.put(Estado.NUEVO_PRODUCTO, addProductoControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_STOCK, mostrarStocksControlador);
        mapaEstadosContoladores.put(Estado.ACTUALIZAR_UNIDADES, incrementarUnidadesStockControlador);
        
        //mapaEstadosContoladores.put(Estado.MOSTRAR_STOCK, old_mostrarStockControlador);
        //mapaEstadosContoladores.put(Estado.ACTUALIZAR_UNIDADES, old_modificarUnidadControlador);
        
        mapaEstadosContoladores.put(Estado.MOSTRAR_TIQUETS, mostrarTicketsControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_VALOR_TIENDA, mostrarTotalValorControlador);
        mapaEstadosContoladores.put(Estado.MOSTRAR_FACTURACION_TOTAL, mostrarTotalFacturacionControlador);        
        mapaEstadosContoladores.put(Estado.RETIRAR_PRODUCTO, eliminarProductoControlador);
        mapaEstadosContoladores.put(Estado.REALIZAR_VENTA, nuevaVentaControlador);
        mapaEstadosContoladores.put(Estado.EXIT, null);
    }
        
    @Override
    public AppControlador getControladorPadre(){          
        return mapaEstadosContoladores.get(manager.getEstado());               
    }
}
