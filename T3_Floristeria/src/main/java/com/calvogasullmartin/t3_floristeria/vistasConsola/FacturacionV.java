package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista;
import java.io.IOException;

public class FacturacionV {
    
    private InOut io;

    public FacturacionV() {
        io = new InOut();
    }
    
    public void interactuar(FacturacionC controlador){
        assert controlador != null;
        try {
            new FloristeriaVista().mostrarFacturacion(controlador.getFacturacion());
            controlador.seleccionarMenu(); 
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }
    }
}
