package com.calvogasullmartin.t3_floristeria.persistencia.dto;

import com.calvogasullmartin.t3_floristeria.modelos.Producto;

public class FlorDto extends Producto{
    
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }    
}
