package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.VistaInterface;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncional;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;

public class AppVista implements VistaInterface{

    //atributos: las vistas que interactuaran con cada controlador que llegue
    private BienvenidaVista bienvenidaVista;
    
    private MenuPrincipalVista menuVista;

    public AppVista() {
        //instanciar los atributos (hacer el new). Sus constructores no tienen parametors
        bienvenidaVista = new BienvenidaVista();
        menuVista = new MenuPrincipalVista();
    }
    
    @Override
    public void interactuar(ControladorFuncional controlador) {
        assert controlador != null;
        controlador.aceptar(this);
    }

    @Override
    public void visitar(ArrancarAppControlador controlador) {
        assert controlador != null;
        bienvenidaVista.interactuar(controlador);
    }
    
    @Override
    public void visitar(MenuControlador controlador) {
        assert controlador != null;
       menuVista.interactuar(controlador);
    }
    
    //private InOut io = new InOut();
    
    /*
    public void interactuar(ControladorFuncional controlador) {
        if (controlador instanceof ArrancarAppControlador) {
            interactuar(controlador);
        }
    }

    public void interactuar(ArrancarAppControlador controlador) {
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
