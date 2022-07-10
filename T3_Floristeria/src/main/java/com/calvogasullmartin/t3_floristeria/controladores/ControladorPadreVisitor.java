package com.calvogasullmartin.t3_floristeria.controladores;

public interface ControladorPadreVisitor {
    
    // una nueva linia
    void visitar (ArrancarAppControlador controlador);
    
    void visitar(MenuControlador controlador);
    
    void visitar(AddProductoControlador controlador);
}
