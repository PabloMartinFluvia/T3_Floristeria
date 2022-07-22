package com.calvogasullmartin.t3_floristeria.modelos;

public class Manager2 {
    
    private Estado2 estado;
    
    private final int NUM_CATEGORIAS = 3; //3 tipos de productos = 3 stocks distintos  
        
    
    private final int MAX_UNIDADES_EN_STOCK = 999999;

    public Manager2() {
        this.estado = Estado2.INITIAL; // cuando se crea el manager se pone el estado inicial
    }

    public Estado2 getEstado() {
        return estado;
    }

    public void setEstado(Estado2 estado) {
        this.estado = estado;
    }

    public int getNUM_CATEGORIAS() {
        return NUM_CATEGORIAS;
    }       

    public int getMAX_UNIDADES_EN_STOCK() {
        return MAX_UNIDADES_EN_STOCK;
    }        
}
