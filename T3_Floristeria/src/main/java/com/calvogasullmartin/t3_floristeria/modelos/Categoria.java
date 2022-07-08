package com.calvogasullmartin.t3_floristeria.modelos;

public enum Categoria {
    
    ARBOL("ÁRBOL"),
    FLOR("FLOR"),
    DECORACION("DECORACIÓN");
    
    private String abreviatura;

    private Categoria(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    @Override
    public String toString(){
        return abreviatura;
    }
}
