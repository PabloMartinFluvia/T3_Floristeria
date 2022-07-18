package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC;
import java.io.IOException;

public interface FacturacionC extends AppC, PersistenciaC{
    
    float getFacturacion() throws IOException;
}
