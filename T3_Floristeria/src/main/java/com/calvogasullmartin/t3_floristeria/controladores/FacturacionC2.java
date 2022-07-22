package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC2;

public interface FacturacionC2 extends PersistenciaC2{
    
    float getFacturacion() throws IOException;
}
