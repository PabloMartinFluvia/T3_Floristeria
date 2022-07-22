package com.calvogasullmartin.t3_floristeria.modelos;

public enum Material2 {
    MADERA("Madera"),
    PLASTICO("Plastico");
    
    private String abreviatura;

    private Material2(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
