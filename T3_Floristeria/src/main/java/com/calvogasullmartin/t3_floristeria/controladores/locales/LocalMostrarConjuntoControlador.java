package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import java.io.IOException;
import java.util.List;

public class LocalMostrarConjuntoControlador extends LocalControladorPadre implements MostrarConjuntoControlador{

    private boolean isStock; // si true se ocupa de stocks, si falso entonces se ocupa de tiquets.
    private boolean withUnits; // si true, devuelve los productos con sus unidades; si false se ignoran las unidades
    
    public LocalMostrarConjuntoControlador(Estados estados, boolean isStock) {
        super(estados);
        this.isStock = isStock;
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    } 

    @Override
    public boolean isIsStock() {
        return isStock;
    }   

    @Override
    public void setWithUnits(boolean withUnits) {
        this.withUnits = withUnits;
    }        

    @Override
    public boolean isWithUnits() {
        return withUnits;
    }

    @Override
    public List<List<String>> getAllConjuntos() throws IOException {
        /*
        pendent
        */
    }

    @Override
    public List<String> getOneConjuntos(int conjundo_id) throws IOException {
        /*
        pendent
        */
    }

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }
    
}
