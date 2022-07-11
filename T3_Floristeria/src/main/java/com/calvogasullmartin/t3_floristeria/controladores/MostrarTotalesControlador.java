package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface MostrarTotalesControlador extends ControladorPadre{

    public boolean isMostrarValorTienda();
    
    public void seleccionarMenu();
    
    public float getTotalStocks() throws IOException;
    
    public float getTotalTiquets() throws IOException;
}
