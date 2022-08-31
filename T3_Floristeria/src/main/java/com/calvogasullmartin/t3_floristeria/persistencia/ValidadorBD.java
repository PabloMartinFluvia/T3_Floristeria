package com.calvogasullmartin.t3_floristeria.persistencia;

import java.io.IOException;
import java.sql.SQLException;

public interface ValidadorBD {
    
    public boolean isBDInicizialized() throws IOException, SQLException; 
        
    public void initBD() throws IOException, SQLException; 
}
