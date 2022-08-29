package com.calvogasullmartin.t3_floristeria.vistas.consola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class FloristeriaV {
    
    private InOut io;
    
    public FloristeriaV() {        
        this.io = new InOut();        
    }
    
    public String askName() {
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
    
    public void printValor(float valor){
        io.writeln("El valor total actual de la floristeria es de: "+valor);
    }
    
    public void printFacturacion(float facturacion){
        io.writeln("El total de la facturación a fecha de hoy es de: "+facturacion);
    }
}
