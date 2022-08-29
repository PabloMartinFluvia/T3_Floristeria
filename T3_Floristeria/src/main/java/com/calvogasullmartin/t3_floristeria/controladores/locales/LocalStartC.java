package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;

public class LocalStartC extends LocalPersistenciaC implements StartC{    
    
    public LocalStartC(Manager manager) {        
        super(manager);
    }    

    @Override
    public boolean isFirstTimeRunning() throws IOException {
        errorBD = "Error! No se ha podido comprovar si existe la BBDD.";
        return !factory.getConector().isBDInicizialized();
    }

    @Override
    public void initBD() throws IOException {
        errorBD = "Error! No se ha podido crear la BBDD.";
        factory.getConector().initBD();
    }

    @Override
    public void saveFloristeria(String nombre) throws IOException {
        assert nombre != null;
        assert nombre.length() >= 3;
        errorBD = "Error! No se ha podido guardar los datos de la floristeria.";                      
        Floristeria floristeria = new Floristeria(this.getNUM_CATEGORIAS());
        floristeria.setNombre_floristeria(nombre); 
        //el id lo pondrá el dao dentro del create  
        factory.getFloristeriaDao().create(floristeria);
    }

    @Override
    public String getFloristeriaName() throws IOException {
        errorBD = "Error! No se ha podido leer los datos de la floristeria.";  
        return factory.getFloristeriaDao().getName();
    }    

    @Override
    public void accept(ControladorVisitador controlador) {     
        assert controlador != null;
        controlador.visit(this);
    }
}
