package com.calvogasullmartin.t3_floristeria.modelos;

public class Manager {
    
    private Estado estado;
    
    private final int NUM_CATEGORIAS = 3; //3 tipos de productos = 3 stocks distintos  
        
    
    private final int MAX_UNIDADES_EN_STOCK = 999999;

    public Manager() {
        this.estado = Estado.INITIAL; // cuando se crea el manager se pone el estado inicial
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getNUM_CATEGORIAS() {
        return NUM_CATEGORIAS;
    }       

    public int getMAX_UNIDADES_EN_STOCK() {
        return MAX_UNIDADES_EN_STOCK;
    }        
}
