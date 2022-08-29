package com.calvogasullmartin.t3_floristeria.modelos;

public class Manager {
    
    private Estado estado;
    
    private final int NUM_CATEGORIAS = 3;         
    
    private final int MAX_UNIDADES_EN_STOCK = 999999;
    
    private final int CANTIDAD_VENTA_MINIMA = 1;

    public Manager() {        
        this.estado = Estado.INITIAL; 
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        assert estado != null;
        this.estado = estado;
    }

    public int getNUM_CATEGORIAS() {
        return NUM_CATEGORIAS;
    }       

    public int getMAX_UNIDADES_EN_STOCK() {
        return MAX_UNIDADES_EN_STOCK;
    }        

    public int getCANTIDAD_VENTA_MINIMA() {
        return CANTIDAD_VENTA_MINIMA;
    }
}
