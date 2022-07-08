package com.calvogasullmartin.t3_floristeria.modelos;

public class Floristeria {
    
    private int id; //1:1 composicion -> embebed
    
    private String nombre; //1:1 composicion -> embebed
    
    private float valorTotal; //1:1 composicion -> embebed
    
    /*
    private Map<Categoria,Stock> stocks; //1:3 composición ->
                            //array de stocls embebed en json / mongo
                            // tabla extra en mysql registros floristeria_id / stock_id        
    
    private List<Ticket> tickets; //1:0_N composición ->
                            //array de tickets embebed en json / mongo
                            // tabla extra en mysql registros floristeria_id / ticket_id
    
    private float ventasToal; //1:1 composicion -> embebed  
    */
   
    public Floristeria() {
        //ningun valor válido -> si no se hacen los setters el programa petará       
        id = 0;
        nombre = null;
        valorTotal = -1f;
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
