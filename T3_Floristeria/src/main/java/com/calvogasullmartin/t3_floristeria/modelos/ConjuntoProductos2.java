package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductos2 {
    // es = stock = tiquet
    private int id; // 1,2,3 para los stock de arboles, flores, decoracion, los siguientes para tiquets
    
    private float valor_Productos; // sumatorio, en este conjunto, de: precio del producto * unidades 
                                    // = valor de este stock = importe de este tiquet
    private List<ProductoUnidad2> productos; // lista de ProductoUnidad2
                                    //asociar producto y unidades de este se puede hacer un Map, pero para serializar hace falta esta clase intermedia

    public ConjuntoProductos2() {
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

    public List<ProductoUnidad2> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoUnidad2> productos) {
        this.productos = productos;
    }
}
