package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoUnidad2 {
    
    private ProductoCompleto2 producto;
    
    private int cantidad;

    public ProductoUnidad2() {
        producto = new ProductoCompleto2();
        cantidad = 0;
    }

    public ProductoCompleto2 getProducto() {
        return producto;
    }

    public void setProducto(ProductoCompleto2 producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }        
}
