package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface RealizarVentaC extends ElegirProductoC, GestionarUnidadesC, Controlador{        
    
    void createTicket() throws IOException, SQLException, MongoException;
    
    int getMinVenta();
    
    boolean isTicketValid();
    
    void resetTicket();           
}
