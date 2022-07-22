package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.CategoriaV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ConjuntoProductosV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;


public class MostradorConjuntosV2{
    
    private boolean withUnits;
    
    private boolean withProductId;
        
    public MostradorConjuntosV2(boolean withUnits, boolean withProductId) {
        this.withUnits = withUnits;
        this.withProductId = withProductId;
    }       
    
    public int showStocks(StocksC2 stocks, boolean isAllPosible, String mensageOptions) throws IOException{
        inicializar(stocks, withUnits, withProductId);
        int stock_id = readStocks(stocks, isAllPosible, mensageOptions);
        mostrarConjuntos(stocks);
        return stock_id;
    }      
    
    public void showTiquets(TicketsC2 tickets) throws IOException{
        inicializar(tickets, withUnits, withProductId);
        readTickets(tickets);
        mostrarConjuntos(tickets);
    }
    
    private void inicializar(ConjuntosC2 controlador, boolean withUnits, boolean withId){
        controlador.setConUnidades(withUnits);
        controlador.setConId(withId);
        controlador.resetModels();
    }
                
    private int readStocks(StocksC2 stocks, boolean isAllPosible, String mensageOptions) throws IOException {
        int indexStock = new CategoriaV2(isAllPosible,mensageOptions).pedirIndexCategoria();
        stocks.getStocks(indexStock);
        return indexStock +1 ;
    }
    
    private void readTickets(TicketsC2 tickets) throws IOException{
        tickets.getAllTiquets();
    }
    
    private void mostrarConjuntos(ConjuntosC2 controlador){
        new ConjuntoProductosV2(controlador).mostrarConjuntos();
    }
    
    
}
