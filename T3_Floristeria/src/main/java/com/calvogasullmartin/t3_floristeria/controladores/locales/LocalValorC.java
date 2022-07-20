package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaC;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;

public class LocalValorC extends LocalPersistenciaC implements ValorC{

    public LocalValorC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public float getValor() throws IOException {
        return factory.getFloristeriaDao().getValorFloristeria();
    }

    
}
