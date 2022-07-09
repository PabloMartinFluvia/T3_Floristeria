package com.calvogasullmartin.t3_floristeria.modelos;

public class Aplicacion {
    //esta clase almacena todos los modelos de nuestra aplicación
    
    private Estado estado;
    
    private Floristeria floristeria;    
    
    //falta menú i otros
    
    private final int NUM_CATEGORIAS = 3; //3 tipos de productos = 3 stocks distintos
    
    private final int NUM_DIVISIONES_CONJUNTOS = 2; // 2 maneras de interpretar un conjunto: stock o tiquet

    public Aplicacion() {
        estado = Estado.INITIAL;
        floristeria = new Floristeria(NUM_CATEGORIAS);        
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Floristeria getFloristeria() {
        return floristeria;
    }

    public void setFloristeria(Floristeria floristeria) {
        this.floristeria = floristeria;
    }
    
    
}
