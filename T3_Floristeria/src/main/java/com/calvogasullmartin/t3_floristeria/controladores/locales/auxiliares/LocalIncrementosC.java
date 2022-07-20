package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementosC;

public abstract class LocalIncrementosC extends LocalPersistenciaC implements IncrementosC {

    protected ProductoUnidad productoUnidad;

    protected int incremento;

    protected float incrementoValor;

    protected float[] incrementoValorStocks;

    public LocalIncrementosC(Manager manager) {
        super(manager);
    }

    @Override
    public abstract void aceptar(AppCVisitorC controlador);

    @Override
    public void resetModels() {
        productoUnidad = new ProductoUnidad();
    }

    @Override
    public void resetIncr() {
        incremento = 0;
    }

    @Override
    public void resetIncrValor() {
        incrementoValor = 0f;
    }

    @Override
    public void resetIncrValorStocks() {
        incrementoValorStocks = new float[getNUM_CATEGORIAS()];
    }

    @Override
    public int getMaxIncr() {
        return this.getMAX_UNIDADES_EN_STOCK() - Math.max(0, getOldCantidad());
    }

    @Override
    public int getMinIncr() {
        return Math.min(0, -getOldCantidad());
    }

    protected int getOldCantidad() {
        return productoUnidad.getCantidad();
    }

    @Override
    public void setIncr(int incremento) {
        this.incremento = incremento;
    }

    @Override
    public abstract void addIncrValor();

    @Override
    public abstract void addIncrValorStock();

    @Override
    public void updateValorTotal() throws IOException {
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);
    }

    @Override
    public void updateValoresStock() throws IOException {
        for (int stock_id = 1; stock_id <= this.getNUM_CATEGORIAS(); stock_id++) {
            float incrementoStock = incrementoValorStocks[stock_id - 1];
            if (incrementoStock != 0) {
                factory.getConjuntoProductosDao().incrementarValorEnStockById(stock_id, incrementoStock);
            }
        }
    }

    protected int getStockId() {
        return productoUnidad.getProducto().categoriaIndex() + 1;
    }

    @Override
    public void setModelsNull() {
        productoUnidad = null;
    }

    @Override
    public void setIncrsNull() {
        incremento = 0;
        incrementoValor = 0f;
        incrementoValorStocks = null;
    }
}
