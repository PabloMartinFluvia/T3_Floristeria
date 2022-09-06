package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public class LocalStartC extends LocalPersistenciaC implements StartC{    
    
    public LocalStartC(Manager manager) {        
        super(manager);
    }    

    @Override
    public boolean isFirstTimeRunning() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha podido comprovar si existe la BBDD.";
        boolean bdInicialized = factory.getValidadorBD().isBDInicizialized();
        if(bdInicialized){
            String name = getFloristeriaName();
            return !(name != null && name.length() >= 3);
        }
        return !bdInicialized;
    }

    @Override
    public void initBD(String nombre) throws IOException, SQLException, MongoException {
        assert nombre != null;
        assert nombre.length() >= 3;
        errorBD = "Error! No se ha podido crear la BBDD.";
        factory.getValidadorBD().initBD();
        saveFloristeria(nombre);
    }

    private void saveFloristeria(String nombre) throws IOException, SQLException, MongoException {
        assert nombre != null;
        assert nombre.length() >= 3;
        errorBD = "Error! No se ha podido guardar los datos de la floristeria.";                      
        Floristeria floristeria = new Floristeria(this.getNUM_CATEGORIAS());
        floristeria.setNombre_floristeria(nombre); 
        //el id lo pondrá el dao dentro del create  
        factory.getFloristeriaDao().create(floristeria);
    }

    @Override
    public String getFloristeriaName() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha podido leer los datos de la floristeria.";  
        return factory.getFloristeriaDao().getName();
    }    

    @Override
    public void accept(ControladorVisitador controlador) {     
        assert controlador != null;
        controlador.visit(this);
    }
}
