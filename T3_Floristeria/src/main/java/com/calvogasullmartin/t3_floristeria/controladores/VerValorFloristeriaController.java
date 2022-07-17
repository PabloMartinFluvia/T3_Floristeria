package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;

public interface VerValorFloristeriaController extends PersistenciaControlador {
    
    /*
    final factory
    
    acceptar 
    setEstado
    getErrorBD
    seleccionarMenu
    */
    
    public float getTotalStocks() throws IOException;
}
