package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaC;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;

public class LocalStartC extends LocalPersistenciaC implements StartC{

    public LocalStartC(Manager manager) {
        super(manager);
    }
        
    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public boolean isPrimeraVez() throws IOException {
        Conector conector = factory.getConector();
        boolean ok = true;
        if(conector.isBDInicizializada()){
            ok=false;
        }
        return ok;
    }

    @Override
    public void iniciarPersistencia() throws IOException {
        factory.getConector().inicializarBD();
    }

    @Override
    public void guardarUnicaFloristeria(String nombre) throws IOException {
        assert nombre != null;
        assert nombre.length() >= 3;
        //el id lo pondrá el dao dentro del create        
        Floristeria floristeria = new Floristeria(this.getNUM_CATEGORIAS());
        floristeria.setNombre_floristeria(nombre); 
        factory.getFloristeriaDao().create(floristeria);
    }

    @Override
    public String getNombreFloristeria() throws IOException {
        return factory.getFloristeriaDao().getName();
    }    

    @Override
    public void seleccionarExit() {
        this.setEstado(Estado.EXIT);
    }
    
}
