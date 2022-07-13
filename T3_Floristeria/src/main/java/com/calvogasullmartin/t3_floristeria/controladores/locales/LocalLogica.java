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
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;

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
    
    private MostrarConjuntoControlador mostrarStockControlador;
    
    private MostrarConjuntoControlador mostrarTicketsControlador;
    
    private MostrarTotalesControlador mostrarTotalValorControlador;
    
    private MostrarTotalesControlador mostrarTotalFacturacionControlador;
    
    private ModificarProductoControlador modificarUnidadControlador;
    
    private ModificarProductoControlador eliminarProductoControlador;
    
    private NuevaVentaControlador nuevaVentaControlador;
    
    private Map<Estado,ControladorPadre> mapaEstadosContoladores;            

    public LocalLogica() {
        aplicacion = new Aplicacion(); 
        estados = new Estados(Estado.INITIAL);        
        this.arrancarAppControlador = new LocalArrancarAppControlador(estados, aplicacion.getFloristeria());
        this.menuControlador = new LocalMenuControlador(estados);
        this.addProductoControlador = new LocalAddProductoControlador(estados);
        this.mostrarStockControlador = new LocalMostrarConjuntoControlador(estados, true); //stocks
        this.mostrarTicketsControlador = new LocalMostrarConjuntoControlador(estados, false); //tiquets
        this.mostrarTotalValorControlador = new LocalMostrarTotalesControlador(estados, true); // totalValorStocks
        this.mostrarTotalFacturacionControlador = new LocalMostrarTotalesControlador(estados, false); //totalValorTiquets
        this.modificarUnidadControlador = new LocalModificarProductoControlador(estados, true); // actualizar unidades de un producto que está en stock
        this.eliminarProductoControlador = new LocalModificarProductoControlador(estados, false); // eliminar un producto que está en stock
        this.nuevaVentaControlador = new LocalNuevaVentaCotnrolador(estados);
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
        return mapaEstadosContoladores.get(estados.getEstado());               
    }
}
