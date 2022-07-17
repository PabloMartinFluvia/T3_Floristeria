package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.StocksController;
import java.io.IOException;

public interface IncrementarUnidadesControlador extends StocksController{
    
    public void seleccionarMenu(); //idem a todos los que salen del menú
    
    boolean isIdValid(int producto_id);
    
    int getMaxIncrement();
    
    int getMinIncrement();
    
    void actualizarCantidad(int incremento) throws IOException;
}
