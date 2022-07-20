package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC;

public interface TiquetsC extends ConjuntosC{
    
    void getAllTiquets() throws IOException;
}
