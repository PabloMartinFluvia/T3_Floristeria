package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.StocksController;

public interface MostrarStocksControlador extends StocksController{
    
    public void seleccionarMenu(); //idem a todos los que salen del menú        
    
    public void setConUnidades(boolean conUnidades);
}
