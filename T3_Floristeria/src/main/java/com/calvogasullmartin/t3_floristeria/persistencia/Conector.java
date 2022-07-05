package com.calvogasullmartin.t3_floristeria.persistencia;

import java.io.IOException;

public interface Conector {
    
    public boolean isBDInicizializada();
    
    public void inicializarBD() throws IOException;
}
