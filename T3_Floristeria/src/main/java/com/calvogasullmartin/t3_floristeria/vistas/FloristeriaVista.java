package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FloristeriaVista {

    private InOut io;

    public FloristeriaVista() {        
        this.io = new InOut();        
    }

    public void interactuar(ArrancarAppControlador controlador) {
        String nombre = pedirNombre();        
        try {
            controlador.iniciarFloristeria(nombre);
            controlador.guardarUnicaFloristeria();
            //io.write("Floristeria guardada con éxito.");
            System.out.println("Floristeria guardada con éxito.");
        } catch (IOException ex) {
            ////que hacer con la excepcion
        }
    }

    private String pedirNombre() {
        String nombre = null;
        boolean ok = false;
        int minNumChar = 3;        
        do {
            //nombre = io.readString("Introduce el nombre de la floristeria: ");
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduce el nombre de la floristeria: ");
            nombre = entrada.nextLine();
            if (nombre.length() < minNumChar) {
                //io.writeError("de al menos 3 caracteres");
                System.out.println("Error: el nombre debe tener al menos 3 caracteres.");
            } else {
                ok = true;
            }
        } while (!ok);
        return nombre;
    }

}
