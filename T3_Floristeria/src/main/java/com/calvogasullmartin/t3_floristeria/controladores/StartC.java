package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface StartC extends Controlador, PersistenciaC{
    
    boolean isFirstTimeRunning() throws IOException, SQLException, MongoException;
    
    void initBD(String name) throws IOException, SQLException, MongoException;
        
    String getFloristeriaName() throws IOException, SQLException, MongoException;                 
}
