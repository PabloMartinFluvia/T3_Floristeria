package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalSeleccionadorC2;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.VentaC2;

public class LocalVentaC2 extends LocalSeleccionadorC2 implements VentaC2{

    ConjuntoProductos2 conjunto;   
    
    ProductoUnidad2 productoVendido;    
    
    public LocalVentaC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC2 controlador) {
        controlador.visistar(this);
    }

    @Override
    public void resetTiquet() {
        conjunto = new ConjuntoProductos2();
        resetModels();
    }

    @Override
    public void addProductoUnidadEnTiquet(int cantidadVendida) {
        productoVendido = new ProductoUnidad2();
        productoVendido.setProducto(productoUnidadSeleccionado.getProducto());
        productoVendido.setCantidad(cantidadVendida);        
        conjunto.getProductos().add(productoVendido);
        incremento = -cantidadVendida;
        
    }
    
    @Override
    public void addIncrValor() {
        incrementoValor += incremento * productoUnidadSeleccionado.getProducto().getPrecio();
    }

    @Override
    public void addIncrValorStock() {
        incrementoValorStocks[getStockId()-1] += incremento * productoUnidadSeleccionado.getProducto().getPrecio();
    }    

    @Override
    public void guardarTiquet() throws IOException {
        conjunto.setValor_Productos(-incrementoValor);
        factory.getConjuntoProductosDao().createTiquet(conjunto);    
        factory.getFloristeriaDao().incrementarFacturacionFloristeria(-incrementoValor);
    }

    @Override
    public void modificarCantidadEnStock() throws IOException {
        int stockId = productoVendido.getProducto().categoriaIndex()+1;
        int productoId = productoVendido.getProducto().getProducto_id();
        factory.getProductoUnidadDao().
                    incrementarCantidadByStockIdProductoId(stockId,productoId,incremento); 
    }
}
