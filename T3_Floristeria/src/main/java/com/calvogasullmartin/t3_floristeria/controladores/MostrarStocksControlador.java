package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface MostrarStocksControlador extends ControladorPadre{
    
    public void seleccionarMenu();
    
    public void getStocks(int stock_index) throws IOException;
    
    public int getNumConjuntosToShow();
    
    public String getStockTitleInStocks(int stockIndex);
            
    public float getStockValueInStocks(int stockIndex);
    
    public int getNumProductsInStocks(int stockIndex);
    
    public int getCantidadProductoInStock(int stockIndex, int productIndex);
    
    public String getIntroProductoInStock(int stockIndex, int productoIndex);
    
    public float getPrecioProductoInStock(int stockIndex, int productoIndex);
    
    public String getDetallesProductoInStock(int stockIndex, int productoIndex);
}
