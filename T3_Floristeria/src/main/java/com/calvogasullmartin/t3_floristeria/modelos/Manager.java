package com.calvogasullmartin.t3_floristeria.modelos;

public class Manager {
    
    private Estado estado;
    
    private final int NUM_CATEGORIAS = 3; //3 tipos de productos = 3 stocks distintos  
    
    final String errorBD = "No se ha podido acceder a la base de datos";

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

    public String getErrorBD() {
        return errorBD;
    }            
}
