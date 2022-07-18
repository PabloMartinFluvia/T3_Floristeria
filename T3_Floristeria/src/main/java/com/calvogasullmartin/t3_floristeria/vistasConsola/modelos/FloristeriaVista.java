package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class FloristeriaVista {
    
    private InOut io;
    
    public FloristeriaVista() {        
        this.io = new InOut();        
    }
    
    public String pedirNombre() {
        String nombre = null;
        boolean ok = false;
        int minNumChar = 3;        
        do {
            nombre = io.readString("Por favor, introduce el nombre de la floristeria: ");            
            if (nombre.length() < minNumChar) {
                io.writeError("de al menos 3 caracteres.");
            } else {
                ok = true;
            }
        } while (!ok);
        return nombre;
    }
}
