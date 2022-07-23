package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.*;


public interface AppCVisitorC2 {
        
    void visitar(StartC controlador);
    
    void visitar(MenuC controlador);
    
    void visitar(ValorC controlador);
    
    void visitar(FacturacionC controlador);
    
    void visitar(NuevoC2 controlador);
    
    void visitar(TicketsC2 controlador);
    
    void visitar(StocksC2 controlador);
    
    void visitar(ModificadorC2 controlador);
    
    void visistar(VentaC2 controlador);
    
    void visistar(EliminarC2 controlador);
}
