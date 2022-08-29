package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ShowValorC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;


public class LocalShowValorC extends LocalPersistenciaC implements ShowValorC{

    public LocalShowValorC(Manager manager) {
        super(manager);
    }

    @Override
    public float getValor() throws IOException {
        errorBD = "Error! No se ha podido leer el valor de la floristería.";
        return factory.getFloristeriaDao().getValorFloristeria();
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        controlador.visit(this);
    }
}
