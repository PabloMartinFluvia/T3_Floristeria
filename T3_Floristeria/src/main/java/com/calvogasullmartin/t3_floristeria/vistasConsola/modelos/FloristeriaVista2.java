package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class FloristeriaVista2 {
    
    private InOut io;
    
    public FloristeriaVista2() {        
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
    
    public void mostrarValor(float valor){
        io.writeln("El valor total actual de la floristeria es de: "+valor);
    }
    
    public void mostrarFacturacion(float facturacion){
        io.writeln("El total de la facturación ha fecha de hoy es de: "+facturacion);
    }
}
