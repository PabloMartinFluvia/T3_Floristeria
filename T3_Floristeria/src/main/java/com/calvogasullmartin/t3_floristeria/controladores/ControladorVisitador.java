package com.calvogasullmartin.t3_floristeria.controladores;

public interface ControladorVisitador {
        
    void visit(StartC controlador);
    
    void visit(MenuC controlador);
    
    void visit(ShowValorC controlador);
    
    void visit(ShowFacturacionC controlador);
    
    void visit(ShowTicketsC controlador);
    
    void visit(ShowStocksC controlador);
    
    void visit(NuevoProductoC controlador);        
    
    void visit(ModificarUnidadesC controlador);
    
    void visit(RealizarVentaC controlador);
    
    void visit(DescatalogarProductoC controlador);
}
