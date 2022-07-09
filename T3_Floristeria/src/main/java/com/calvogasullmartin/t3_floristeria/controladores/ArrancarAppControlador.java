package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class ArrancarAppControlador extends ControladorFuncional{
    
    private Floristeria floristeria;
    //private FloristeriaDao floristeriaDao;//sustituido

    public ArrancarAppControlador(Estado estado, Floristeria floristeria) {
        super(estado);
        this.floristeria = floristeria;
    }

    @Override
    public void controlar() {   
    }

    public String getNombreFloristeria() throws IOException{
        return factory.getFloristeriaDao().findName();
    }
    
    public void setEstado(Estado estado){//codigo repetido
        this.estado = estado;
    }

    public getNombreFloristeria(){
        return floristeriaDao.findName();
    }

    public isPrimeraVez(){
        Conector conector = DaoFactory.getFactory().getConector();
        boolean t = true;
        if(conector.isBDInicizializada()){
            t=false;
        }
        else{
            conector.inicializarBD();
        }
        return t;
    }

    public void guardarUnicaFloristeria (String nombre) throws IOException{
        assert nombre != null;
        floristeria.setFloristeria_id(1);
        floristeria.setNombre_floristeria(nombre);
        factory.getFloristeriaDao().create(floristeria);
    }  
}
