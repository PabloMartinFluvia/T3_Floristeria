package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalConjuntosC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC;

public class LocalTicketsC extends LocalConjuntosC implements TicketsC{

    public LocalTicketsC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public String getTitle(int indexConjunto) {
        assert indexConjunto < conjuntos.size();
        return "TIQUET (id: "+conjuntos.get(indexConjunto).getId()+"):";
    }

    @Override
    public String getMensageCantidad() {
        return "vendida";
    }

    @Override
    public void getAllTiquets() throws IOException {
        conjuntos = factory.getConjuntoProductosDao().getAllTiquets();
    }
    
}
