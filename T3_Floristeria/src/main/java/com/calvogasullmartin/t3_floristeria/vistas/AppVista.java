package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppVista {

    private InOut io = new InOut();

    public AppVista() {
    }

    public void interactua(ControladorFuncional controlador) {
        if (controlador instanceof ArrancarAppControlador) {
            interactua(controlador);
        }
    }

    public void interactua(ArrancarAppControlador controlador) {
        BienvenidaVista bienvenida = new BienvenidaVista(controlador);

        if (controlador.isPrimeraVez()) {
            // sacar mensaje "es la primera vez que se arranca..."
           new FloristeriaVista(controlador).solicitarDatos();
           //mensaje de guardado con exito
           controlador.setEstado(Estado.IN_MENU);
        } else {
            String nombre;
            try {
                nombre = controlador.getNombreFloristeria();
                io.write("Bienvenidro a la App de la floristeria " + nombre);
                controlador.setEstado(Estado.IN_MENU);
            } catch (IOException ex) {
                System.err.println("No se ha podido acceder a la BD");
            }

        }
    }

}
