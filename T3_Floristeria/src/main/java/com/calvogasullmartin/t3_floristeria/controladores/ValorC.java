package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC;
import java.io.IOException;

public interface ValorC extends PersistenciaC{
    
    float getValor() throws IOException;
}
