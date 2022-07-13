package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import java.io.IOException;

public class BienvenidaVista {

    final String errorBD = "No se ha podido acceder a la base de datos";
    
    private InOut io;
    private ArrancarAppControlador controlador;

    public BienvenidaVista() {
        io = new InOut();
    }
    
    public void interactuar(ArrancarAppControlador controlador) {
        assert controlador != null;
        this.controlador = controlador; 
        if (controlador.isPrimeraVez()) {
            pedirInicializacion();
        }
        darBienvienida();
        controlador.seleccionarMenu(); //FIN DE LA FUNCIONALIDAD
    }

    private void pedirInicializacion() {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        //System.out.println("Aplicación ejecutandose por primera vez ...");
        try {
            controlador.iniciarPersistencia();
            io.writeln("Capa de persistencia inizializada con exito");
            //System.out.println("Capa de persistencia inizializada con exito.");                        
            new FloristeriaVista().interactuar(controlador);
        } catch (IOException ex) {
            io.writeln(errorBD);
            //System.err.println(errorBD);
        }
    }

    private void darBienvienida() {
        String nombre;
        try {
            nombre = controlador.getNombreFloristeria();
            io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin.");
            //System.out.println("Bienvenid@ a " + nombre + "'s Manager, desarrollado por CalvoGasullMartin.\n");
        } catch (IOException ex) {
            io.writeln(errorBD);
            //System.err.println(errorBD);
        }
    }
}
