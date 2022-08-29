package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface StartC extends Controlador, PersistenciaC{
    
    boolean isFirstTimeRunning() throws IOException;
    
    void initBD() throws IOException;
    
    void saveFloristeria(String nombre) throws IOException;
        
    String getFloristeriaName() throws IOException;                 
}
