package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductos {
    // es = stock = tiquet
    private int id; // 1,2,3 para los stock de arboles, flores, decoracion, los siguientes para tiquets
    
    private float valor_Productos; // sumatorio, en este conjunto, de: precio del producto * unidades 
                                    // = valor de este stock = importe de este tiquet
    private List<ProductoUnidad> productos; // lista de ProductoUnidad
                                    //asociar producto y unidades de este se puede hacer un Map, pero para serializar hace falta esta clase intermedia

    public ConjuntoProductos() {
        this.id = 0;
        valor_Productos = 0f;
        productos = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor_Productos() {
        return valor_Productos;
    }

    public void setValor_Productos(float valor_Productos) {
        this.valor_Productos = valor_Productos;
    }

    public List<ProductoUnidad> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoUnidad> productos) {
        this.productos = productos;
    }
    
    public int getCantidadProductoByIndex(int productoIndex){
        return this.productos.get(productoIndex).getCantidad();
    }
    
    public float getPrecioProductoByIndex(int productoIndex){
        return this.productos.get(productoIndex).getPrecioProductoByIndex(productoIndex);        
    }
    
    public String getIntroProductoByIndex(int productoIndex){
        return this.productos.get(productoIndex).getIntroProductoByIndex(productoIndex);
    }
    
    public String getDetallesProductoByIndex(int productoIndex){
        return this.productos.get(productoIndex).getDetallesProductoByIndex(productoIndex);
    }

    
    
    
    
    
    

    public String listaToString(){
        String string = "";
        if(productos.size()!=0)
            for (ProductoUnidad productoUnidad : productos) {
                string = string + productoUnidad.productoToString();
            }
        return string;
    }
    
    public String listaToStringQ(){
        String string = "";
        if(productos.size()!=0)
            for (ProductoUnidad productoUnidad : productos) {
                string = string + productoUnidad.productoToStringQ();
            }
        return string;
    }
    
    public String listaToStringTicket(){
        String string = "Valor total: "+valor_Productos+".\n";
        if(productos.size()!=0)
            for (ProductoUnidad productoUnidad : productos) {
                string = string + productoUnidad.productoToString();
            }
        return string;
    }
}
