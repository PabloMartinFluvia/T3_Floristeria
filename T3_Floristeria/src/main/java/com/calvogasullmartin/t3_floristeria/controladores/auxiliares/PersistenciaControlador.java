package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;

public interface PersistenciaControlador extends AppControlador{
    
    final DaoFactory factory = DaoFactory.getFactory();
    
    String getErrorBD();
    
    public void seleccionarMenu();
}
