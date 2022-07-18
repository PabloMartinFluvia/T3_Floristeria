package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista;
import java.io.IOException;

public class StartV {
    
    private InOut io;

    public StartV() {
        io = new InOut();
    }
    
    public void interactuar(StartC controlador) {
        assert controlador != null;
        try {
            if (controlador.isPrimeraVez()) {
                inizializar(controlador);
            }
            darBienvienida(controlador);
            controlador.seleccionarMenu(); 
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
            controlador.seleccionarExit();
        }
    }    
    
    private void inizializar(StartC controlador) throws IOException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.iniciarPersistencia();
        io.writeln("Capa de persistencia inizializada con exito");   
        String nombre = new FloristeriaVista().pedirNombre();
        controlador.guardarUnicaFloristeria(nombre);
        io.writeln("Floristeria guardada con éxito.");
        io.writeln();
    }

    private void darBienvienida(StartC controlador) throws IOException {
        String nombre = controlador.getNombreFloristeria();              
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");        
        io.writeln();
    }
}
