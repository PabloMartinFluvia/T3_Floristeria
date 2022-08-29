package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ShowFacturacionC  extends Controlador, PersistenciaC{
    
    float getFacturacion() throws IOException;
}
