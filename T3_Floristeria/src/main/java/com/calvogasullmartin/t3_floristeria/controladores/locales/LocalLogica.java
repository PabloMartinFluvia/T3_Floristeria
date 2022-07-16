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
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD_MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD_ModificarProductoControlador;

public class LocalLogica implements Logica{    
        
    private Manager manager; 

    private ArrancarAppControlador arrancarAppControlador;     
    
    private MenuControlador menuControlador;
    
    private AddProductoControlador addProductoControlador;
    
    private MostrarStocksControlador mostrarStocksControlador;
    
    private IncrementarUnidadesStockControlador incrementarUnidadesStockControlador;
    
    
    
    private Z_OLD_MostrarConjuntoControlador mostrarTicketsControlador;    
    private MostrarTotalesControlador mostrarTotalValorControlador;    
    private MostrarTotalesControlador mostrarTotalFacturacionControlador;            
    private Z_OLD_ModificarProductoControlador eliminarProductoControlador;    
    private NuevaVentaControlador nuevaVentaControlador;
    
    
    private Z_OLD_MostrarConjuntoControlador old_mostrarStockControlador;
    private Z_OLD_ModificarProductoControlador old_modificarUnidadControlador;
    
    private Map<Estado,ControladorPadre> mapaEstadosContoladores;            

    public LocalLogica() {        
        manager = new Manager();    
        this.arrancarAppControlador = new LocalArrancarAppControlador(manager);
        this.menuControlador = new LocalMenuControlador(manager);
        this.addProductoControlador = new LocalAddProductoControlador(manager);
        this.mostrarStocksControlador = new LocalMostrarStocksControlador(manager);
        this.incrementarUnidadesStockControlador = new LocalIntrementarUnidadesStockControlador(manager);
        
        
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
    public ControladorPadre getControladorPadre(){          
        return mapaEstadosContoladores.get(manager.getEstado());               
    }
}
