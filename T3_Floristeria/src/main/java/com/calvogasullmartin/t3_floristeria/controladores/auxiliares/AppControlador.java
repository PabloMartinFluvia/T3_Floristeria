package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;


public interface AppControlador {        
    
    void aceptar(AppControladorVisitor visitor);    

    public void setEstado(Estado estado);
        
}
