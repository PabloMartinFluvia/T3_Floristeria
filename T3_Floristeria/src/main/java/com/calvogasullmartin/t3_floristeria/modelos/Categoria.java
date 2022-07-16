package com.calvogasullmartin.t3_floristeria.modelos;

public enum Categoria {
    
    ARBOL("Árboles"),
    FLOR("Flores"),
    DECORACION("Decoraciones");
    
    private String abreviatura;

    private Categoria(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
