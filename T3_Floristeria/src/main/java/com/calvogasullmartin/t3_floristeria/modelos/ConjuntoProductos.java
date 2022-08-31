package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductos {

    // 1,2,3 para los stock de arboles, flores, decoracion, los siguientes para tiquets
    private int conjunto_id; 
    
    private float valor_Productos; 
    
    private List<ProductoUnidad> productos; // productos + las unidades de este en el conjunto                                    

    public ConjuntoProductos() {
        this.conjunto_id = 0;
        valor_Productos = 0f;
        productos = new LinkedList<>();
    }

    public int getConjunto_id() {
        return conjunto_id;
    }

    public void setConjunto_id(int conjunto_id) {
        this.conjunto_id = conjunto_id;
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
    
    public int cantidadProducto(int productoIndex){
        return productos.get(productoIndex).getCantidad();
    }
    
    public int idProducto(int productoIndex){
        return productos.get(productoIndex).idProducto();
    }
    
    public float precioProducto(int productoIndex){
        return productos.get(productoIndex).precioProducto();
    }
    
    public String detallesProducto(int productoIndex){
        return productos.get(productoIndex).detallesProducto();
    }
}
