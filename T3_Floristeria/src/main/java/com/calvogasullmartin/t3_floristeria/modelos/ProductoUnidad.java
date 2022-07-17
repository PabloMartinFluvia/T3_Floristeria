package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoUnidad {
    
    private ProductoCompleto producto;
    
    private int cantidad;

    public ProductoUnidad() {
        producto = null;
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
    
    
    
    ///métodes problematics per a la persistencia
    public float obtainPrecioProducto (){
        return this.producto.getPrecio();        
    }
    
    public String obtainIntroProducto(){
        return this.producto.obtainIntroProducto();
    }
    
    public String obtainDetallesProducto(){
        return this.producto.obtainDetallesProducto();
    }
    
    public int obtainProductoId(){
        return this.producto.getProducto_id();
    }
    
    public int obtainIndexCategoria(){
        return this.producto.obtainCategoriaIndex();
    }
    
    
    
    
    
    public String productoToString(){
        String string = producto.toString();
        return string + ".\n";
    }
    
    public String productoToStringQ(){
        String string = producto.toString();
        return string + ", "+ cantidad + ".\n";
    }
    
}
