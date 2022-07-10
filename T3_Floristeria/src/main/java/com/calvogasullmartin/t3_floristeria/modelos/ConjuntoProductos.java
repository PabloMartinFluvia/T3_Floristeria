package com.calvogasullmartin.t3_floristeria.modelos;

import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductos {
    // es = stock = tiquet
    private Integer id; // 1,2,3 para los stock de arboles, flores, decoracion, los siguientes para tiquets
    
    private Float valor_Productos; // sumatorio, en este conjunto, de: precio del producto * unidades 
                                    // = valor de este stock = importe de este tiquet
    private List<ProductoUnidad> productos; // lista de ProductoUnidad
                                    //asociar producto y unidades de este se puede hacer un Map, pero para serializar hace falta esta clase intermedia

    public ConjuntoProductos(Integer id) {
        this.id = id;
        valor_Productos = 0f;
        productos = new LinkedList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValor_Productos() {
        return valor_Productos;
    }

    public void setValor_Productos(Float valor_Productos) {
        this.valor_Productos = valor_Productos;
    }

    public List<ProductoUnidad> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoUnidad> productos) {
        this.productos = productos;
    }    
}
