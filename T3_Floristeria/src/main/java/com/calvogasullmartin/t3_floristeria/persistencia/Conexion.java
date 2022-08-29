package com.calvogasullmartin.t3_floristeria.persistencia;

import java.io.IOException;

public interface Conexion {
    
    public boolean isBDInicizialized() throws IOException; 
        
    public void initBD() throws IOException; 
}
