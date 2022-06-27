package com.calvogasullmartin.t3_floristeria.persistencia.dto;

import com.calvogasullmartin.t3_floristeria.modelos.Producto;

public class ArbolDto extends Producto{
    
    private float altura; // unidades en metros

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
