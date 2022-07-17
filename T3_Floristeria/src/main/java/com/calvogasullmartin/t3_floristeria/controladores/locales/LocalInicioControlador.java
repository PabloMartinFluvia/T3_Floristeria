package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.InicioControlador;


public class LocalInicioControlador extends LocalPersistenciaControlador implements InicioControlador{        
    
    //private Floristeria floristeria;
    private int numCategorias;    
    
    public LocalInicioControlador(Manager manager) {        
        super(manager);   
        numCategorias = manager.getNUM_CATEGORIAS();        
        //this.floristeria = new Floristeria(manager.getNUM_CATEGORIAS());
    }
    
    @Override
    public boolean isPrimeraVez() throws IOException{
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
        Floristeria floristeria = new Floristeria(numCategorias);
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
    
    @Override
    public void aceptar(AppControladorVisitor visitor) {
        assert visitor != null;
        visitor.visitar(this);
    }          

    
}