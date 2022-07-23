package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ValorC{
    
    float getValor() throws IOException;
    
    String getErrorBD();
    
    void selectMenu();
}
