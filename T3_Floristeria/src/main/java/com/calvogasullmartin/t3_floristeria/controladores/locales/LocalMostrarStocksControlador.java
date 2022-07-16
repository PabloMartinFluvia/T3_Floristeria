package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import java.util.List;

public class LocalMostrarStocksControlador extends LocalControladorPadre implements MostrarStocksControlador{           
    
    private final int NUM_CATEGORIAS;
    
    private List<ConjuntoProductos> stocks;
    
    private ConjuntoProductos stock;
    
    private int numStocksToShow;
    
    public LocalMostrarStocksControlador(Manager manager) {
        super(manager);        
        this.NUM_CATEGORIAS = manager.getNUM_CATEGORIAS();
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }
    
    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public void getStocks(int stock_index) throws IOException{        
        if(stock_index<0){//all
            getAllStocks();
        } else {            
            getOneStock(stock_index);
        }
    }
    
    private void getAllStocks() throws IOException{   
        stocks = factory.getConjuntoProductosDao().getAllStocks();  
        numStocksToShow = stocks.size();
    }
    
    private void getOneStock(int stock_index) throws IOException{
        assert stock_index >= 0 && stock_index<NUM_CATEGORIAS;
        stock = factory.getConjuntoProductosDao().getOneStockById(stock_index + 1); 
        numStocksToShow = 1;
    }               

    @Override
    public int getNumConjuntosToShow() {
        return numStocksToShow;
    }

    @Override
    public String getStockTitleInStocks(int stockIndex) {
        if(numStocksToShow == 1){                        
            return "STOCK: "+Categoria.values()[stock.getId()-1];
        }else{            
            return "STOCK: "+Categoria.values()[stocks.get(stockIndex).getId()-1];        
        }
    }

    @Override
    public float getStockValueInStocks(int stockIndex) {
        if(numStocksToShow == 1){                        
            return stock.getValor_Productos();
        }else{            
            return stocks.get(stockIndex).getValor_Productos();
        }
    }

    @Override
    public int getNumProductsInStocks(int stockIndex) {
        if(numStocksToShow == 1){                        
            return stock.getProductos().size();
        }else{            
            return stocks.get(stockIndex).getProductos().size();
        }
    }

    @Override
    public int getCantidadProductoInStock(int stockIndex, int productIndex) {
        if(numStocksToShow == 1){                        
            return stock.getCantidadProductoByIndex(productIndex);
        }else{            
            return stocks.get(stockIndex).getCantidadProductoByIndex(productIndex);
        }        
    }

    @Override
    public String getIntroProductoInStock(int stockIndex, int productoIndex) {
        if(numStocksToShow == 1){                        
            return stock.getIntroProductoByIndex(productoIndex);
        }else{            
            return stocks.get(stockIndex).getIntroProductoByIndex(productoIndex);
        }
    }

    @Override
    public float getPrecioProductoInStock(int stockIndex, int productoIndex) {
        if(numStocksToShow == 1){                        
            return stock.getPrecioProductoByIndex(productoIndex);
        }else{            
            return stocks.get(stockIndex).getPrecioProductoByIndex(productoIndex);
        }
    }

    @Override
    public String getDetallesProductoInStock(int stockIndex, int productoIndex) {
        if(numStocksToShow == 1){                        
            return stock.getDetallesProductoByIndex(productoIndex);
        }else{            
            return stocks.get(stockIndex).getDetallesProductoByIndex(productoIndex);
        }
    }

    
}
