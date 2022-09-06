package com.calvogasullmartin.t3_floristeria.persistencia;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ValidadorBD {
    
    public boolean isBDInicizialized() throws IOException, SQLException, MongoException; 
        
    public void initBD() throws IOException, SQLException, MongoException; 
}
