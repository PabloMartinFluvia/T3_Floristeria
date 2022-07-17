package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.TotalesController;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;


public class LocalTotalesController extends LocalPersistenciaControlador implements TotalesController{

    public LocalTotalesController(Manager manager) {
        super(manager);
    }
    
    @Override
    public void aceptar(AppControladorVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public float getTotalStocks() throws IOException {        
        return factory.getFloristeriaDao().getValorFloristeria();
    }

    
    
}
