package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoUnidad {
    
    private ProductoCompleto producto;
    
    private Integer cantidad;

    public ProductoUnidad() {
        producto = null;
        cantidad = null;
    }

    public ProductoCompleto getProducto() {
        return producto;
    }

    public void setProducto(ProductoCompleto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }        
}
