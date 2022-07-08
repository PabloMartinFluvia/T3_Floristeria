package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;


public class ArrancarAppControlador extends ControladorFuncional{
    
    private Floristeria floristeria;

    public ArrancarAppControlador(Estado estado, Floristeria floristeria) {
        super(estado);
        this.floristeria = floristeria;
    }
    
    public boolean isPrimeraVez(){
        /*
        some code pendiente
        */
        return false;
    }
    
    public String getNombreFloristeria() throws IOException{
        return factory.getFloristeriaDao().findName();
    }
    
    public void guardarUnicaFloristeria (String nombre) throws IOException{
        assert nombre != null;
        floristeria.setFloristeria_id(1);
        floristeria.setNombre_floristeria(nombre);
        factory.getFloristeriaDao().create(floristeria);
    }        
}
