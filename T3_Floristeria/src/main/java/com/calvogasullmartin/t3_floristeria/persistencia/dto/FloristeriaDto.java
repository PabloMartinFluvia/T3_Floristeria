package com.calvogasullmartin.t3_floristeria.persistencia.dto;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;

import java.util.List;
import java.util.Map;

public class FloristeriaDto {
    
    private int id; //1:1 composicion -> embebed
    
    private String nombre; //1:1 composicion -> embebed
    
    private Map<Categoria,StockDto> stocks; //1:3 composición ->
                            //array de stocls embebed en json / mongo
                            // tabla extra en mysql registros floristeria_id / stock_id
    
    private float valorTotal; //1:1 composicion -> embebed
    
    private List<TicketDto> tickets; //1:0_N composición ->
                            //array de tickets embebed en json / mongo
                            // tabla extra en mysql registros floristeria_id / ticket_id
    
    private float ventasToal; //1:1 composicion -> embebed    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<Categoria, StockDto> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Categoria, StockDto> stocks) {
        this.stocks = stocks;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public float getVentasToal() {
        return ventasToal;
    }

    public void setVentasToal(float ventasToal) {
        this.ventasToal = ventasToal;
    }    
}
