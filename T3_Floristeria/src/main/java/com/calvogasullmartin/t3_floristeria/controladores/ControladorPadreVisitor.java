package com.calvogasullmartin.t3_floristeria.controladores;

public interface ControladorPadreVisitor {
        
    void visitar (ArrancarAppControlador controlador);
    
    void visitar(MenuControlador controlador);
    
    void visitar(AddProductoControlador controlador);
    
    void visitar(MostrarConjuntoControlador controlador);
    
    void visitar(MostrarTotalesControlador controlador);
    
    void visitar(ModificarProductoControlador controlador);
    
    void visitar(NuevaVentaControlador controlador);
}
