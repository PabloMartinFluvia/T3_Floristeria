package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ShowFacturacionC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalShowFacturacionC extends LocalPersistenciaC implements ShowFacturacionC{    

    public LocalShowFacturacionC(Manager manager) {
        super(manager);
    }

    @Override
    public float getFacturacion() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha podido leer la facturación total de la floristería.";
        return factory.getFloristeriaDao().getFacturacionFloristeria();
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        controlador.visit(this);
    }
}
