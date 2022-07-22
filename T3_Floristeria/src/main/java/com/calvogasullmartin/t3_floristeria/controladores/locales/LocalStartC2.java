package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaC2;
import com.calvogasullmartin.t3_floristeria.modelos.Estado2;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector2;
import com.calvogasullmartin.t3_floristeria.controladores.StartC2;

public class LocalStartC2 extends LocalPersistenciaC2 implements StartC2{

    public LocalStartC2(Manager2 manager) {
        super(manager);
    }
        
    @Override
    public void aceptar(AppCVisitorC2 controlador) {
        controlador.visitar(this);
    }

    @Override
    public boolean isPrimeraVez() throws IOException {
        Conector2 conector = factory.getConector();
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
        Floristeria2 floristeria = new Floristeria2(this.getNUM_CATEGORIAS());
        floristeria.setNombre_floristeria(nombre); 
        factory.getFloristeriaDao().create(floristeria);
    }

    @Override
    public String getNombreFloristeria() throws IOException {
        return factory.getFloristeriaDao().getName();
    }    

    @Override
    public void seleccionarExit() {
        this.setEstado(Estado2.EXIT);
    }
    
}
