package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.CategoriaV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ConjuntoProductosV;
import java.io.IOException;


public class MostradorConjuntosV{
    
    private boolean withUnits;
    
    private boolean withProductId;
        
    public MostradorConjuntosV(boolean withUnits, boolean withProductId) {
        this.withUnits = withUnits;
        this.withProductId = withProductId;
    }       
    
    public void showStocks(StocksC stocks, boolean isAllPosible, String mensageOptions) throws IOException{
        inicializar(stocks, withUnits, withProductId);
        readStocks(stocks, isAllPosible, mensageOptions);
        mostrarConjuntos(stocks);
    }      
    
    public void showTiquets(TicketsC tickets) throws IOException{
        inicializar(tickets, withUnits, withProductId);
        readTickets(tickets);
        mostrarConjuntos(tickets);
    }
    
    private void inicializar(ConjuntosC controlador, boolean withUnits, boolean withId){
        controlador.setConUnidades(withUnits);
        controlador.setConId(withId);
        controlador.resetModels();
    }
                
    private void readStocks(StocksC stocks, boolean isAllPosible, String mensageOptions) throws IOException {
        int indexStock = new CategoriaV(isAllPosible,mensageOptions).pedirIndexCategoria();
        stocks.getStocks(indexStock);
    }
    
    private void readTickets(TicketsC tickets) throws IOException{
        tickets.getAllTiquets();
    }
    
    private void mostrarConjuntos(ConjuntosC controlador){
        new ConjuntoProductosV(controlador).mostrarConjuntos();
    }
    
    
}
