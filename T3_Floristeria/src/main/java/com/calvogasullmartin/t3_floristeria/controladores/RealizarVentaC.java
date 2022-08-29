package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface RealizarVentaC extends ElegirProductoC, GestionarUnidadesC, Controlador{        
    
    void createTicket() throws IOException;
    
    int getMinVenta();
    
    boolean isTicketValid();
    
    void resetTicket();           
}
