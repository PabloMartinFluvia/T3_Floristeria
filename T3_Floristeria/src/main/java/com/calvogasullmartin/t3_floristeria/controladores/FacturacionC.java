package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface FacturacionC {
    
    float getFacturacion() throws IOException;
    
    String getErrorBD();
    
    void selectMenu();
}
