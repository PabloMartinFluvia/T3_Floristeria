package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector;
import java.io.IOException;


public class LocalArrancarAppControlador extends LocalControladorFuncional implements ArrancarAppControlador{        
    
    private Floristeria floristeria;
    
    public LocalArrancarAppControlador(Estado estado, Floristeria floristeria) {
        super(estado);      
        this.floristeria = floristeria;
    }
    
    @Override
    public boolean isPrimeraVez() {
        Conector conector = factory.getConector();
        boolean ok = true;
        if(conector.isBDInicizializada()){
            ok=false;
        }
        else{
            ok = true;
        }
        return ok;
    }

   @Override
    public void iniciarPersistencia() throws IOException {
        factory.getConector().inicializarBD();
    } 
    

    @Override
    public void iniciarFloristeria(String nombre) {
        floristeria.setFloristeria_id(1);//provisional
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
    public void aceptar(ControladorFuncionalVisitor controlador) {
        controlador.visitar(this);
    }          
}