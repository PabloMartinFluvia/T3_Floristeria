package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.StartC2;

public class StartV2 {
    
    private InOut io;
    
    private StartC2 controlador;

    public StartV2(StartC2 controlador) {
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interactuar() {
        assert controlador != null;
        try {
            if (controlador.isPrimeraVez()) {
                inizializar();
            }
            darBienvienida();
            controlador.seleccionarMenu(); 
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
            controlador.seleccionarExit();
        }
    }    
    
    private void inizializar() throws IOException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.iniciarPersistencia();
        io.writeln("Capa de persistencia inizializada con exito");   
        String nombre = new FloristeriaVista2().pedirNombre();
        controlador.guardarUnicaFloristeria(nombre);
        io.writeln("Floristeria guardada con éxito.");
        io.writeln();
    }

    private void darBienvienida() throws IOException {
        String nombre = controlador.getNombreFloristeria();              
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");        
        io.writeln();
    }
}
