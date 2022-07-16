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
    
    public float getPrecioProductoByIndex (int productoIndex){
        return this.producto.getPrecio();        
    }
    
    public String getIntroProductoByIndex(int productoIndex){
        return this.producto.getIntroProducto();
    }
    
    public String getDetallesProductoByIndex(int productoIndex){
        return this.producto.getDetallesProducto();
    }
    
    public int getProductoId(){
        return this.producto.getProducto_id();
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
