package com.calvogasullmartin.t3_floristeria.controladores;

public interface AppCVisitor {
        
    void visitar(StartC controlador);
    
    void visitar(MenuC controlador);
    
    void visitar(ValorC controlador);
    
    void visitar(FacturacionC controlador);
    
}
