package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.FloristeriaV;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class StartV {
    
    private final InOut io;
    
    private final StartC controlador;

    public StartV(StartC controlador) {
        assert controlador != null;   
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interact() {  
        EndV endV = new EndV(controlador);
        try {            
            if (controlador.isFirstTimeRunning()) {
                init();                
            }
            printWelcome();
            endV.setEstadoMenu();
        } catch (IOException ex) {
            endV.manageError();
        }
    }            
    
    private void init() throws IOException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.initBD();
        io.writeln("Capa de persistencia inizializada con exito");   
        getFloristeriaName();
    }
    
    private void getFloristeriaName() throws IOException {
        String name = new FloristeriaV().askName();
        controlador.saveFloristeria(name);
        io.writeln("Floristeria guardada con éxito.");
        io.writeln();   
    }

    private void printWelcome() throws IOException {
        String nombre = controlador.getFloristeriaName();              
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");        
        io.writeln();
    }
}
