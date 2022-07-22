package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.*;


public interface AppCVisitorC2 {
        
    void visitar(StartC2 controlador);
    
    void visitar(MenuC2 controlador);
    
    void visitar(ValorC2 controlador);
    
    void visitar(FacturacionC2 controlador);
    
    void visitar(NuevoC2 controlador);
    
    void visitar(TicketsC2 controlador);
    
    void visitar(StocksC2 controlador);
    
    void visitar(ModificadorC2 controlador);
    
    void visistar(VentaC2 controlador);
}
