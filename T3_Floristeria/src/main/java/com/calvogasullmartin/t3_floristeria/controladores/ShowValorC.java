package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ShowValorC extends Controlador, PersistenciaC{
    
    float getValor() throws IOException;
}
