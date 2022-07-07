package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FloristeriaVista {
    
    private ArrancarAppControlador controlador;
    private InOut io;

    public FloristeriaVista(ArrancarAppControlador controlador) {
        this.controlador = controlador;
        this.io = new InOut();
    }
        
    
    public void solicitarDatos(){
        // pedir nombre al usuario // validar que sea correcto
        String nombre = null;
        try {
            controlador.guardarUnicaFloristeria(nombre);
        } catch (IOException ex) {
            System.err.println("dgsd");
        }
        
    }
}
