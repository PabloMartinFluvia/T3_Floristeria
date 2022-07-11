package com.calvogasullmartin.t3_floristeria.controladores;

public interface ModificarProductoControlador extends MostrarConjuntoControlador{
    
    //ojo, pk hereda de MostrarConjuntoControlador (para heredar sus metodos). Dará problemas en accept / visit?
    
     public boolean IsUpdateUnits();
}
