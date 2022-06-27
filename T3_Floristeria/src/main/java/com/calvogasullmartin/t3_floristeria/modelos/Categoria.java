package com.calvogasullmartin.t3_floristeria.modelos;

public enum Categoria {
    ARBOL("Arbol"),
    FLOR("Flor"),
    DECORACION("Decoracion");
    
    private String abreviatura;

    private Categoria(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
