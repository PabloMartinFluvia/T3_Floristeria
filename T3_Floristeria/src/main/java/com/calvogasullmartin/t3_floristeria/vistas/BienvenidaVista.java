package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControladorInterface;
import com.calvogasullmartin.t3_floristeria.utils.InOut;

public class BienvenidaVista {
    private InOut io;

    public BienvenidaVista() {
        io = new InOut();
    }
    
    //solo interactua con un ArrncarAppControlador cualquiera
    public void interactuar(ArrancarAppControladorInterface controlador){
        assert controlador != null;
        if (controlador.isPrimeraVez()){
            controlador.iniciarPersistencia();
            io.writeln("Capa de persistencia inizializada con exito");
            new FloristeriaVista().interactuar(controlador);
            
        }
        String nombre = controlador.getNombreFloristeria();
        darBienvienida(nombre);
    }
    
    private void darBienvienida(String nombre){    
        assert nombre != null;        
        io.writeln("Bienvenid@ al gestor de la floristeria "+nombre+", desarrollado por CalvoGasullMartin ");
    }
}
