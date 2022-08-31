package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class Floristeria {
    
    private int floristeria_id; 
    
    private String nombre_floristeria; 
    
    private float valorStocksTotal; 
    
    private float valorTicketsTotal; 
    
    private ConjuntoProductos[] stocks;
    
    private List<ConjuntoProductos> tiquets;
   
    public Floristeria(int numCategorias) {        
        floristeria_id = 0; 
        nombre_floristeria = null;
        valorStocksTotal = 0f;
        valorTicketsTotal = 0f;
        stocks = new ConjuntoProductos[numCategorias]; 
        for (int i = 0; i< numCategorias; i++ ){
            stocks[i] = new ConjuntoProductos();
            stocks[i].setConjunto_id(i+1);
        }
        tiquets = new LinkedList<>(); 
    }

    public int getFloristeria_id() {
        return floristeria_id;
    }

    public void setFloristeria_id(int floristeria_id) {
        this.floristeria_id = floristeria_id;
    }

    public String getNombre_floristeria() {
        return nombre_floristeria;
    }

    public void setNombre_floristeria(String nombre_floristeria) {
        this.nombre_floristeria = nombre_floristeria;
    }

    public float getValorStocksTotal() {
        return valorStocksTotal;
    }

    public void setValorStocksTotal(float valorStocksTotal) {
        this.valorStocksTotal = valorStocksTotal;
    }

    public float getValorTicketsTotal() {
        return valorTicketsTotal;
    }

    public void setValorTicketsTotal(float valorTicketsTotal) {
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
