package com.calvogasullmartin.t3_floristeria.controladores;

public interface MenuC extends Controlador{
    
    String getMenuMessage();
    
    int[] getRangeMenuOptions();   
    
    void changeState(int opcion);
}
