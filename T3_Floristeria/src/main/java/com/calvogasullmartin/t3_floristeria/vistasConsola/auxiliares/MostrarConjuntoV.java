package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ConjuntoProductosV;
import java.io.IOException;

public abstract class MostrarConjuntoV {    
    
    protected void inicializar(ConjuntosC controlador, boolean withUnits, boolean withId){
        controlador.setConUnidades(withUnits);
        controlador.setConId(withId);
        controlador.resetModels();
    }
    
    protected abstract void readConjunto() throws IOException;
    
    protected void mostrarConjuntos(ConjuntosC controlador){
        new ConjuntoProductosV(controlador).mostrarConjuntos();
    }            
}
