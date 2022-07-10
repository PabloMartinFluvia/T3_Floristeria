package com.calvogasullmartin.t3_floristeria.zDescartes.modelos;

import java.util.Map;

public abstract class ProductoManager {
    
    private int id;
    
    private Map<Producto, Integer> productos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<Producto, Integer> productos) {
        this.productos = productos;
    }    
}
