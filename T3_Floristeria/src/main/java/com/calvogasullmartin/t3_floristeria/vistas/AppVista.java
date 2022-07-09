package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.VistaInterface;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControladorInterface;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;

public class AppVista implements VistaInterface{

    //atributos: las vistas que interactuaran con cada controlador que llegue
    private BienvenidaVista bienvenidaVista;

    public AppVista() {
        //instanciar los atributos (hacer el new). Sus constructores no tienen parametors
        bienvenidaVista = new BienvenidaVista();
    }


    @Override
    public void interactua(ControladorFuncional controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(ArrancarAppControladorInterface arrancarAppControlador) {
        bienvenidaVista.interactua(arrancarAppControlador);
    }
    
    //private InOut io = new InOut();
    
    /*
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
*/
}
