package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitorC;

public class LocalFacturacionC extends LocalPersistenciaC implements FacturacionC{

    public LocalFacturacionC(Manager manager) {
        super(manager);
    }
    
    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public float getFacturacion() throws IOException {
        return factory.getFloristeriaDao().getFacturacionFloristeria();
    }

    
    
}
