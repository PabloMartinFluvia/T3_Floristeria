package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.Vista;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadre;

public class AppVista implements Vista{

    //atributos: las vistas que interactuaran con cada controlador que llegue
    private BienvenidaVista bienvenidaVista;
    
    private MenuPrincipalVista menuVista;

    public AppVista() {
        //instanciar los atributos (hacer el new). Sus constructores no tienen parametors
        bienvenidaVista = new BienvenidaVista();
        menuVista = new MenuPrincipalVista();
    }
    
    @Override
    public void interactuar(ControladorPadre controlador) {
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
    public void interactuar(ControladorPadre controlador) {
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
