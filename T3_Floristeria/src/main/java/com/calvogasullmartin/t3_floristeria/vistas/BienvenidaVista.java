package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControladorInterface;
import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class BienvenidaVista {

    private InOut io;
    private ArrancarAppControladorInterface controlador;

    public BienvenidaVista() {
        io = new InOut();
    }

    //solo interactua con un ArrncarAppControlador cualquiera
    public void interactuar(ArrancarAppControladorInterface controlador) {        
        assert controlador != null;
        this.controlador = controlador; //lo guardamos en atributos para no tener que pasarselo a los métodos privados
        if (controlador.isPrimeraVez()) {            
            pedirInicializacion();
        }
        darBienvienida();
        controlador.seleccionarMenu(); //FIN DE LA FUNCIONALIDAD
    }

    private void pedirInicializacion() {
        io.writeln("Aplicación ejecutandose por primera vez ...");
        controlador.iniciarPersistencia();
        io.writeln("Capa de persistencia inizializada con exito");
        new FloristeriaVista().interactuar(controlador);
    }

    private void darBienvienida() {
        String nombre = controlador.getNombreFloristeria();
        io.writeln("Bienvenid@ al gestor de la floristeria " + nombre + ", desarrollado por CalvoGasullMartin ");
    }
}
