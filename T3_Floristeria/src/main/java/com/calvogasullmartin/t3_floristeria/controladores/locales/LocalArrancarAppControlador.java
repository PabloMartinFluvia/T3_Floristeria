package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Aplicacion;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;


public class LocalArrancarAppControlador extends LocalControladorPadre implements ArrancarAppControlador{        
    
    private Floristeria floristeria;
    
    public LocalArrancarAppControlador(Estados estados, Floristeria floristeria) {        
        super(estados);    
        this.floristeria = floristeria;
    }
    
    @Override
    public boolean isPrimeraVez() {
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
    public void iniciarFloristeria(String nombre) {
        assert nombre != null;
        assert nombre.length() >= 3;
        //el id lo pondrá el dao dentro del create
        floristeria.setNombre_floristeria(nombre);        
    }

    @Override
    public void guardarUnicaFloristeria() throws IOException {
        factory.getFloristeriaDao().create(floristeria);
    }

    @Override
    public String getNombreFloristeria() throws IOException {
        return factory.getFloristeriaDao().findName();
    }

    @Override
    public void seleccionarMenu() {          
        this.setEstado(Estado.EN_MENU); 
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        assert controlador != null;
        controlador.visitar(this);
    }          
}