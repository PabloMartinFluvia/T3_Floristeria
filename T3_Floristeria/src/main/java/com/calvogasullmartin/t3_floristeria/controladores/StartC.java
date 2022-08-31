package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import java.sql.SQLException;

public interface StartC extends Controlador, PersistenciaC{
    
    boolean isFirstTimeRunning() throws IOException, SQLException;
    
    void initBD(String name) throws IOException, SQLException;
        
    String getFloristeriaName() throws IOException, SQLException;                 
}
