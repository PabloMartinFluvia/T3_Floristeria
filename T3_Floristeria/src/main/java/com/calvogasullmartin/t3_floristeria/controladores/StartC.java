    package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface StartC{
    
    boolean isPrimeraVez() throws IOException;
    
    void inicizlizarBD() throws IOException;
    
    void saveFloristeria(String nombre) throws IOException;
        
    String getFloristeriaName() throws IOException;     
    
    String getErrorBD();
    
    void selectMenu();
    
    void selectExit();
    
}
