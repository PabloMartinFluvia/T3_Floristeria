package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.FloristeriaV;
import com.calvogasullmartin.t3_floristeria.controladores.StartC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.mongodb.MongoException;
import java.sql.SQLException;

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
        } catch (IOException|SQLException | MongoException ex) {
            endV.manageError(ex);
        }
    }            
    
    private void init() throws IOException, SQLException, MongoException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        String name = new FloristeriaV().askName();
        controlador.initBD(name);
        io.writeln("Capa de persistencia inizializada con exito");  
        io.writeln();
    }

    private void printWelcome() throws IOException, SQLException, MongoException {
        String nombre = controlador.getFloristeriaName();              
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");        
        io.writeln();
    }
}
