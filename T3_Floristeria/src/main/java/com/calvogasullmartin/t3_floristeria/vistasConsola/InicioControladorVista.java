package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.FloristeriaVista;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.InicioControlador;

public class InicioControladorVista {

    private InOut io;
    private InicioControlador controlador;

    public InicioControladorVista() {
        io = new InOut();
    }

    public void interactuar(InicioControlador controlador) {
        assert controlador != null;
        // se puede guardar como atributo, ya que el tiempo de vida de esta vista es muy limitado
        this.controlador = controlador;
        try {
            if (controlador.isPrimeraVez()) {
                pedirInicializacion();
            }
            darBienvienida();
            controlador.seleccionarMenu(); //FIN DE LA FUNCIONALIDAD
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
            controlador.seleccionarExit();
        }        
    }

    private void pedirInicializacion() throws IOException {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.iniciarPersistencia();
        io.writeln("Capa de persistencia inizializada con exito");   
        String nombre = new FloristeriaVista().pedirNombre();
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
