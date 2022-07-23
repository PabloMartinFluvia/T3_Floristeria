package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaC2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;

public class LocalFacturacionC2 extends LocalPersistenciaC2 implements FacturacionC{

    public LocalFacturacionC2(Manager2 manager) {
        super(manager);
    }
    
    @Override
    public void aceptar(AppCVisitorC2 controlador) {
        controlador.visitar(this);
    }

    @Override
    public float getFacturacion() throws IOException {
        return factory.getFloristeriaDao().getFacturacionFloristeria();
    }

    
    
}
