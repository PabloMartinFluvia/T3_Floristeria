package com.calvogasullmartin.t3_floristeria.modelos;

public enum Material {
    MADERA("Madera"),
    PLASTICO("Plastico");
    
    private String abreviatura;

    private Material(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
