package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalPersistenciaC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.UnidadesC;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;

public abstract class LocalUnidadesC extends LocalPersistenciaC implements UnidadesC{

    protected ProductoUnidad productoUnidad;
    
    protected int incremento;
    
    protected float incrementoValor;
    
    protected float[] incrementoValorStocks;
    
    public LocalUnidadesC(Manager manager) {
        super(manager);
    }
    
    @Override
    public abstract void aceptar(AppCVisitor controlador);

    @Override
    public void resetProductoUnidad() {
        productoUnidad = new ProductoUnidad();
    }
    
    @Override
    public void resetIncr() {
        incremento = 0;
    }
    
    @Override
    public void resetIncrValor(){
        incrementoValor = 0f;        
    }
    
    @Override
    public void resetIncrValorStocks(){
        incrementoValorStocks = new float[getNUM_CATEGORIAS()];
    }

    @Override
    public int getMaxIncr() {
        return this.getMAX_UNIDADES_EN_STOCK() - Math.max(0,getOldCantidad());
    }

    @Override
    public int getMinIncr() {
        return Math.min(0, -getOldCantidad());
    }
    
    
    protected int getOldCantidad(){       
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
    public void updateValorTotal() throws IOException{
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);
    }

    @Override
    public void updateValoresStock() throws IOException{
        for (int stockIndex = 0; stockIndex < this.getNUM_CATEGORIAS(); stockIndex++){
            float incrementoStock = incrementoValorStocks[stockIndex];
            if(incrementoStock != 0){
                factory.getConjuntoProductosDao().incrementarValorEnStockById(stockIndex, incrementoStock);
            }
        }
    }
    
    protected int getStockId(){
        return productoUnidad.getProducto().categoriaIndex()+1;
    }
}
