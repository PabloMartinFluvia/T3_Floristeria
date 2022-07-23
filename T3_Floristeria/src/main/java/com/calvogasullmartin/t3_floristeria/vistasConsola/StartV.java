package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaV;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class StartV {
    
    private InOut io;
    
    private StartC controlador;

    public StartV(StartC controlador) {
        assert controlador != null;   
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interactuar() {                     
        try {
            if (controlador.isPrimeraVez()) {
                inizializar();                
            }
            darBienvienida();
            controlador.selectMenu();
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
            controlador.selectExit();
        }
    }            
    
    private void inizializar() throws IOException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.inicizlizarBD();
        io.writeln("Capa de persistencia inizializada con exito");   
        guardarNombre();
    }
    
    private void guardarNombre() throws IOException {
        String nombre = new FloristeriaV().askName();
        controlador.saveFloristeria(nombre);
        io.writeln("Floristeria guardada con éxito.");
        io.writeln();   
    }

    private void darBienvienida() throws IOException {
        String nombre = controlador.getFloristeriaName();              
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");        
        io.writeln();
    }
}
