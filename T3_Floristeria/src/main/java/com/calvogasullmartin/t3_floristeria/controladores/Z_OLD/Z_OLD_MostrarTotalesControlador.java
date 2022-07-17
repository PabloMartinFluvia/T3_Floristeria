package com.calvogasullmartin.t3_floristeria.controladores.Z_OLD;


import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;

public interface Z_OLD_MostrarTotalesControlador extends PersistenciaControlador{
    
    public float getTotalStocks() throws IOException;
    
    public float getTotalTiquets() throws IOException;
}
