package com.calvogasullmartin.t3_floristeria.controladores;

public interface AppCVisitorC {
        
    void visitar(StartC controlador);
    
    void visitar(MenuC controlador);
    
    void visitar(ValorC controlador);
    
    void visitar(FacturacionC controlador);
    
    void visitar(NuevoC controlador);
    
    void visitar(TicketsC controlador);
    
    void visitar(StocksC controlador);
    
    void visitar(ModificadorC controlador);
}
