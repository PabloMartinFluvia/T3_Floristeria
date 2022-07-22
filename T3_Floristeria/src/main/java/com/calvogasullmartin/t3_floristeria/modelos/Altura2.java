package com.calvogasullmartin.t3_floristeria.modelos;

public enum Altura2 {
    MUY_ALTO("Muy alto"),
    ALTO("Alto"),
    MEDIANO("Mediano"),
    BAJO("Bajo"),
    MUY_BAJO("Muy bajo");
    
    private String abreviatura;

    private Altura2(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
