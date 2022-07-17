package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.StocksController;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;

public abstract class LocalStocksController extends LocalPersistenciaControlador implements StocksController {

    protected final int NUM_CATEGORIAS;

    protected boolean conUnidades;

    protected List<ConjuntoProductos> stocks;

    protected ConjuntoProductos stock;

    protected int numStocksToShow;

    public LocalStocksController(Manager manager) {
        super(manager);
        this.NUM_CATEGORIAS = manager.getNUM_CATEGORIAS();
    }

    @Override
    public abstract void aceptar(AppControladorVisitor visitor);

    @Override
    public boolean isConUnidades() {
        return conUnidades;
    }

    @Override
    public void getStocks(int stock_index) throws IOException {
        if (stock_index < 0) {//all
            getAllStocks();
        } else {
            getOneStock(stock_index);
        }
    }

    protected void getAllStocks() throws IOException {
        stocks = factory.getConjuntoProductosDao().getAllStocks();
        numStocksToShow = stocks.size();
    }

    protected void getOneStock(int stock_index) throws IOException {
        assert stock_index >= 0 && stock_index < NUM_CATEGORIAS;
        stock = factory.getConjuntoProductosDao().getOneStockById(stock_index + 1);
        numStocksToShow = 1;
    }

    @Override
    public int getNumConjuntosToShow() {
        return numStocksToShow;
    }

    @Override
    public boolean isStockEmpty() {
        return stock.getProductos().isEmpty();
    }

    @Override
    public String getStockTitleInStocks(int stockIndex) {
        if (numStocksToShow == 1) {
            return "STOCK: " + Categoria.values()[stock.getId() - 1];
        } else {
            return "STOCK: " + Categoria.values()[stocks.get(stockIndex).getId() - 1];
        }
    }

    @Override
    public float getStockValueInStocks(int stockIndex) {
        if (numStocksToShow == 1) {
            return stock.getValor_Productos();
        } else {
            return stocks.get(stockIndex).getValor_Productos();
        }
    }

    @Override
    public int getNumProductsInStocks(int stockIndex) {
        if (numStocksToShow == 1) {
            return stock.getProductos().size();
        } else {
            return stocks.get(stockIndex).getProductos().size();
        }
    }

    @Override
    public int getCantidadProductoInStock(int stockIndex, int productIndex) {
        if (numStocksToShow == 1) {
            return stock.obtainCantidadProductoByIndex(productIndex);
        } else {
            return stocks.get(stockIndex).obtainCantidadProductoByIndex(productIndex);
        }
    }

    @Override
    public String getIntroProductoInStock(int stockIndex, int productoIndex) {
        if (numStocksToShow == 1) {
            return stock.obtainIntroProductoByIndex(productoIndex);
        } else {
            return stocks.get(stockIndex).obtainIntroProductoByIndex(productoIndex);
        }
    }

    @Override
    public float getPrecioProductoInStock(int stockIndex, int productoIndex) {
        if (numStocksToShow == 1) {
            return stock.obtainPrecioProductoByIndex(productoIndex);
        } else {
            return stocks.get(stockIndex).obtainPrecioProductoByIndex(productoIndex);
        }
    }

    @Override
    public String getDetallesProductoInStock(int stockIndex, int productoIndex) {
        if (numStocksToShow == 1) {
            return stock.obtainDetallesProductoByIndex(productoIndex);
        } else {
            return stocks.get(stockIndex).obtainDetallesProductoByIndex(productoIndex);
        }
    }
}
