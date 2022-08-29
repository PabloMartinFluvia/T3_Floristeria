package com.calvogasullmartin.t3_floristeria.persistencia;

import java.io.IOException;

public interface Conector {
    
    public boolean isBDInicizialized() throws IOException; //ok
        
    public void initBD() throws IOException; //ok   
}
