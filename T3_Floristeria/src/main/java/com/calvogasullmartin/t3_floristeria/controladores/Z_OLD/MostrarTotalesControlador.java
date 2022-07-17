package com.calvogasullmartin.t3_floristeria.controladores.Z_OLD;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppControlador;

public interface MostrarTotalesControlador extends PersistenciaControlador{

    public boolean isMostrarValorTienda();
    
    public void seleccionarMenu();
    
    public float getTotalStocks() throws IOException;
    
    public float getTotalTiquets() throws IOException;
}
