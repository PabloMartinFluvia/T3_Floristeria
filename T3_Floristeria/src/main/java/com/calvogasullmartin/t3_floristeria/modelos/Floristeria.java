package com.calvogasullmartin.t3_floristeria.modelos;

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
        valorStocksTotal = null;
        valorTicketsTotal = null;
        stocks = new ConjuntoProductos[3]; // elements = null
        ti
    }

    public void setId(int id) {
        assert id == 1; // la aplicacion es solo para una floristeria
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        assert nombre != null;
        //faltan mas validaciones
        this.nombre = nombre;
    }
    
    public void setValorTotal(float valorTotal) {
        assert valorTotal >= 0f; 
        this.valorTotal = valorTotal;
    }
    
    public String getNombre() {
        assert nombre != null;
        //faltan mas validaciones
        return nombre;
    }    
    
    /*
    public int getId() {
        assert id == 1;
        return id;
    } 
    
    public float getValorTotal() {
        assert valorTotal >= 0f; 
        return valorTotal;
    }

    public Map<Categoria, Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Categoria, Stock> stocks) {
        this.stocks = stocks;
    }    

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public float getVentasToal() {
        return ventasToal;
    }

    public void setVentasToal(float ventasToal) {
        this.ventasToal = ventasToal;
    } 
    */
}
