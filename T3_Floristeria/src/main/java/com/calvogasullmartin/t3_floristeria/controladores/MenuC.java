package com.calvogasullmartin.t3_floristeria.controladores;

public interface MenuC{
    
    String getMenuMensage();
    
    int getNumOptionsNoExit();   
    
    void canviarEstado(int opcion);
    
    void selectExit();
}
