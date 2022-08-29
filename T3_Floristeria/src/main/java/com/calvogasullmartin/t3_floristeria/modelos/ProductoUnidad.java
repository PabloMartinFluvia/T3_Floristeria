package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoUnidad {
    
    private ProductoCompleto producto;
    
    private int cantidad;

    public ProductoUnidad() {
        producto = new ProductoCompleto();
        cantidad = 0;
    }

    public ProductoCompleto getProducto() {
        return producto;
    }

    public void setProducto(ProductoCompleto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }        
    
    public int idProducto(){
        return producto.getProducto_id();
    }
    
    public float precioProducto(){
        return producto.getPrecio();
    }
    
    public String detallesProducto(){
        return producto.detallesProducto();
    }
}
