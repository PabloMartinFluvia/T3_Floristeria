package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.VentaC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalSeleccionadorC;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;

public class LocalVentaC extends LocalSeleccionadorC implements VentaC{

    ConjuntoProductos conjunto;    
    
    float valorConjunto;
    
    float[] canvioValorStocks;
    
    public LocalVentaC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visistar(this);
    }

    @Override
    public void addIncrValor() {
        /*
        not necesari
        */
    }

    @Override
    public void addIncrValorStock() {
        /*
        not necesari
        */
    }

    @Override
    public void modificarCantidadEnStock() throws IOException {
        /*
        not necesari
        */
    }

    @Override
    public void resetTiquet() {
        conjunto = new ConjuntoProductos();
    }

    @Override
    public void addProductoUnidadEnTiquet(int cantidadVendida) {
        ProductoUnidad productoVendido = new ProductoUnidad();
        productoVendido.setProducto(productoUnidad.getProducto());
        productoVendido.setCantidad(cantidadVendida);        
        conjunto.getProductos().add(productoVendido);
        
    }

    @Override
    public void guardarTiquet() throws IOException {
        calcularValorTotal();
        factory.getConjuntoProductosDao().createTiquet(conjunto);
        for(ProductoUnidad productoVendido : conjunto.getProductos()){
            int stockId = productoVendido.getProducto().categoriaIndex()+1;
            int productoId = productoVendido.getProducto().getProducto_id();
            int cantidadVendida = productoVendido.getCantidad();
            factory.getProductoUnidadDao().
                    incrementarCantidadByStockIdProductoId(stockId,productoId,-cantidadVendida);
        }
        factory.getFloristeriaDao().incrementarValorFloristeria(-valorConjunto);
        for(int i = 0; i < getNUM_CATEGORIAS(); i++){
            factory.getConjuntoProductosDao().incrementarValorEnStockById(i+1, -canvioValorStocks[i]);
        }
        
    }
    
    private void calcularValorTotal(){
        valorConjunto = 0f;
        canvioValorStocks = new float[getNUM_CATEGORIAS()];
        for(ProductoUnidad productoVendido : conjunto.getProductos()){
            int cantidad = productoVendido.getCantidad();
            float precio = productoVendido.getProducto().getPrecio();
            float valor = cantidad * precio;
            valorConjunto += valor;
            int stocIndex = productoVendido.getProducto().categoriaIndex();
            canvioValorStocks[stocIndex] += valor;
        }
    }
    
}
