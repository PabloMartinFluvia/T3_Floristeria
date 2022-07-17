package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.VerValorFloristeriaController;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;


public class LocalVerValorFloristeriaController extends LocalPersistenciaControlador implements VerValorFloristeriaController{

    public LocalVerValorFloristeriaController(Manager manager) {
        super(manager);
    }
    
    @Override
    public void aceptar(AppControladorVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public float getTotalStocks() throws IOException {        
        return factory.getFloristeriaDao().getValorFloristeria();
    }

    
    
}
