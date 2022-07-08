package com.calvogasullmartin.t3_floristeria.modelos;

public enum Altura {
    MUY_ALTO("MUY_ALTO"),
    ALTO("ALTO"),
    MEDIANO("MEDIANO"),
    BAJO("BAJO"),
    MUY_BAJO("MUY_BAJO");
    
    private String abreviatura;

    private Altura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
