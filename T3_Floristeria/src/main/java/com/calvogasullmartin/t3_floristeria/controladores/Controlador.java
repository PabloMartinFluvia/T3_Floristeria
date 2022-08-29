package com.calvogasullmartin.t3_floristeria.controladores;

/*
Todos los controladores almacenados en la lógica implementan este método,
para que así la implementación de Vista los pueda distinguir.
*/

public interface Controlador {        
    
    void accept(ControladorVisitador controlador);               
}
