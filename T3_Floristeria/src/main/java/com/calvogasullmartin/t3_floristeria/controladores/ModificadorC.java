package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC;
import java.io.IOException;

public interface ModificadorC extends SeleccionadorC {
    
    public void modificarCantidadEnStock() throws IOException;
    
}
