package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LocalNuevaVentaCotnrolador extends LocalControladorPadre implements NuevaVentaControlador{
    
    private ConjuntoProductos nuevoTiquet;        
    
    private ProductoUnidad nuevoProductoUnidadEnTiquet;
    
    private ProductoUnidad productoUnidadEnStock;
    
    private int cantidadVendida;
    
    private float importeTiquet;
    
    private float[] disminucionValor;
    
    private List<ProductoUnidad> productosUnidadEnStockPostVenta;
    
    public LocalNuevaVentaCotnrolador(Estados estados) {
        super(estados);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public void inicializarTiquet() {        
        this.nuevoTiquet = new ConjuntoProductos(); //sin id
        disminucionValor = new float[Categoria.values().length];
        productosUnidadEnStockPostVenta = new LinkedList<>();
    }

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public List<String> getAllStocks() throws IOException {
        List<ConjuntoProductos> stocks = factory.getConjuntoProductosDao().findAllStocks();        
        List<String> stocksParsedToListString = new LinkedList<>();
        for(ConjuntoProductos stock: stocks){
            stocksParsedToListString.add(conjuntoToString(stock,true));
        }
        return stocksParsedToListString;
    }
    
    //COPY PASTE
    private String conjuntoToString (ConjuntoProductos conjunto, boolean withCantidad){
        assert conjunto != null; 
        String conjuntoToString = null;
        /*
        passar el conjunto a string. Teniendo en cuenta de si hay que mostrar o no la cantidad del ProductoUnidad
        */
        return conjuntoToString;
    }

    @Override
    public boolean isProductUnidadInSomeStock(int producto_id) throws IOException{
        ProductoCompleto producto = factory.getProductoCompletoDao().findById(producto_id);
        if (producto != null){
            int stock_id = producto.getCategoria().ordinal()+1;            
            this.productoUnidadEnStock = factory.getProductoUnidadesDao().findByStockIdAndProductoId(stock_id, producto_id);
            this.nuevoProductoUnidadEnTiquet = new ProductoUnidad();             
            nuevoProductoUnidadEnTiquet.setProducto(producto);    
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isSuficienteStockParaLaVenta(int cantidad) {
        if(cantidad >=this.productoUnidadEnStock.getCantidad()){                        
            this.cantidadVendida = cantidad;
            return true;
        }
        return false;
    }

    @Override
    public void addToTiquet() {      
        this.nuevoProductoUnidadEnTiquet.setCantidad(cantidadVendida);
        float importeProductoUnidad = cantidadVendida * nuevoProductoUnidadEnTiquet.getProducto().getPrecio();
        importeTiquet += importeProductoUnidad;
        nuevoTiquet.setValor_Productos(importeTiquet);
        nuevoTiquet.getProductos().add(nuevoProductoUnidadEnTiquet);  
        //no id
        
        disminucionValor[nuevoProductoUnidadEnTiquet.getProducto().getCategoria().ordinal()+1] -= importeProductoUnidad;
        int oldCantidad = productoUnidadEnStock.getCantidad();
        productoUnidadEnStock.setCantidad(oldCantidad - cantidadVendida);
        productosUnidadEnStockPostVenta.add(productoUnidadEnStock);
    }

    @Override
    public void guardarTiquet() throws IOException {
        factory.getConjuntoProductosDao().createTiquet(nuevoTiquet);
    }

    @Override
    public void updateStockValues() throws IOException {        
        
        for(ProductoUnidad productoUnidad: productosUnidadEnStockPostVenta){
            int stock_id = productoUnidad.getProducto().getCategoria().ordinal()+1;
            factory.getProductoUnidadesDao().updateInConjunto(productoUnidad, stock_id);            
        }
        
        for(int i = 0; i < Categoria.values().length; i++){
            //actualizar valor de ese stock
            factory.getConjuntoProductosDao().incrementarValorUnStockById(i+1, disminucionValor[i]);
        }
        
        factory.getFloristeriaDao().incrementarValorFloristeria(-importeTiquet);
        
    }


    
}
