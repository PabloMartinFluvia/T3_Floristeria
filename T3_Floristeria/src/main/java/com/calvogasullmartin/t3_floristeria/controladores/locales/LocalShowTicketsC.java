package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ShowTicketsC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalShowTicketsC extends LocalShowConjuntosC implements ShowTicketsC{

    public LocalShowTicketsC(Manager manager) {
        super(manager);
    }

    @Override
    public void readConjuntos() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha poddido leer el histórico de tickets.";
        conjuntosLista = factory.getConjuntoProductosDao().getAllTiquets();        
    }

    @Override
    public String getTitle() {
        return "TIQUET (id: "+conjuntoToShow.getConjunto_id()+"):";
    }
    
    @Override
    public String getMensageCantidad() {
        return "vendida";
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        assert controlador != null;
        controlador.visit(this);
    }
}
