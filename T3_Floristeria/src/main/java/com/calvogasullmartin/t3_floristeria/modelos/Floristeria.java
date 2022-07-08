package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class Floristeria {
    
    private Integer floristeria_id; 
    
    private String nombre_floristeria; 
    
    private Float valorStocksTotal; 
    
    private Float valorTicketsTotal; 
    
    private ConjuntoProductos[] stocks;
    
    private List<ConjuntoProductos> tiquets;
   
    public Floristeria() {
        // constructor -> null o conjuntos vacíos  
        floristeria_id = null; 
        nombre_floristeria = null;
        valorStocksTotal = 0f;
        valorTicketsTotal = 0f;
        stocks = new ConjuntoProductos[3]; // elements = null        
        tiquets = new LinkedList<>(); // size = 0
    }

    public Integer getFloristeria_id() {
        return floristeria_id;
    }

    public void setFloristeria_id(Integer floristeria_id) {
        this.floristeria_id = floristeria_id;
    }

    public String getNombre_floristeria() {
        return nombre_floristeria;
    }

    public void setNombre_floristeria(String nombre_floristeria) {
        this.nombre_floristeria = nombre_floristeria;
    }

    public Float getValorStocksTotal() {
        return valorStocksTotal;
    }

    public void setValorStocksTotal(Float valorStocksTotal) {
        this.valorStocksTotal = valorStocksTotal;
    }

    public Float getValorTicketsTotal() {
        return valorTicketsTotal;
    }

    public void setValorTicketsTotal(Float valorTicketsTotal) {
        this.valorTicketsTotal = valorTicketsTotal;
    }

    public ConjuntoProductos[] getStocks() {
        return stocks;
    }

    public void setStocks(ConjuntoProductos[] stocks) {
        this.stocks = stocks;
    }

    public List<ConjuntoProductos> getTiquets() {
        return tiquets;
    }

    public void setTiquets(List<ConjuntoProductos> tiquets) {
        this.tiquets = tiquets;
    }   
}
