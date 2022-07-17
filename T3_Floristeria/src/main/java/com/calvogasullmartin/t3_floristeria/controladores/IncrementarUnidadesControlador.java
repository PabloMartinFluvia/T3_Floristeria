package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.StocksController;
import java.io.IOException;

public interface IncrementarUnidadesControlador extends StocksController{                
    
    boolean isIdValid(int producto_id);
    
    int getMaxIncrement();
    
    int getMinIncrement();
    
    void actualizarCantidad(int incremento) throws IOException;
    
    public void incrementarValores() throws IOException;
}
