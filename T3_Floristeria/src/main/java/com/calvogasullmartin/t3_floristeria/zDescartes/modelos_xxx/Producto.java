package com.calvogasullmartin.t3_floristeria.zDescartes.modelos_xxx;


public abstract class Producto {
    
    private int id;
    
    private float precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }        
}
