package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControladorInterface;
import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class FloristeriaVista {
    
    private InOut io;

    public FloristeriaVista() {        
        this.io = new InOut();
    }
        
    public void interactuar(ArrancarAppControladorInterface controlador){
        String nombre = pedirNombre();
        controlador.iniciarFloristeria(nombre);
        controlador.guardarUnicaFloristeria();
        io.write("Floristeria guardada con éxito.");
    }
    
    private String pedirNombre(){
        String nombre = null;
        boolean ok = false;
        int minNumChar = 3;
        do{
            nombre = io.readString("Introduce el nombre de la floristeria: ");
            if (nombre.length() < minNumChar){
                io.writeError("de al menos 3 caracteres");
            }else {
                ok = true;
            }
        }while(!ok);
        return null;
    }
    
}
