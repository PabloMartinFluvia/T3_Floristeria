package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class Floristeria2 {
    
    private int floristeria_id; 
    
    private String nombre_floristeria; 
    
    private float valorStocksTotal; 
    
    private float valorTicketsTotal; 
    
    private ConjuntoProductos2[] stocks;
    
    private List<ConjuntoProductos2> tiquets;
   
    public Floristeria2(int numCategorias) {
        // constructor -> null o conjuntos vacíos  
        floristeria_id = 0; 
        nombre_floristeria = null;
        valorStocksTotal = 0f;
        valorTicketsTotal = 0f;
        stocks = new ConjuntoProductos2[numCategorias]; // elements = null 
        for (int i = 0; i< numCategorias; i++ ){
            stocks[i] = new ConjuntoProductos2(); 
            stocks[i] = new ConjuntoProductos2();
            stocks[i].setId(i+1);
        }
        tiquets = new LinkedList<>(); // size = 0
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

    public ConjuntoProductos2[] getStocks() {
        return stocks;
    }

    public void setStocks(ConjuntoProductos2[] stocks) {
        this.stocks = stocks;
    }

    public List<ConjuntoProductos2> getTiquets() {
        return tiquets;
    }

    public void setTiquets(List<ConjuntoProductos2> tiquets) {
        this.tiquets = tiquets;
    }   
}
