package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import java.sql.SQLException;

public interface RealizarVentaC extends ElegirProductoC, GestionarUnidadesC, Controlador{        
    
    void createTicket() throws IOException, SQLException;
    
    int getMinVenta();
    
    boolean isTicketValid();
    
    void resetTicket();           
}
