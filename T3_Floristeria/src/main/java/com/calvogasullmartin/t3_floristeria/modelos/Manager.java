package com.calvogasullmartin.t3_floristeria.modelos;

public class Manager {
    
    private Estado estado;
    
    private final int NUM_CATEGORIAS = 3; //3 tipos de productos = 3 stocks distintos    

    public Manager(Estado estado) {
        this.estado = estado;
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
            
}
