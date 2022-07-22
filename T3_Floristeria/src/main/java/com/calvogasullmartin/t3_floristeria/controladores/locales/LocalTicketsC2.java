package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalConjuntosC2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.TicketsC2;

public class LocalTicketsC2 extends LocalConjuntosC2 implements TicketsC2{

    public LocalTicketsC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC2 controlador) {
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
