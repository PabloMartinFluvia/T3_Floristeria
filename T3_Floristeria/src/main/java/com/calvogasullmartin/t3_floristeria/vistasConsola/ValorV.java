package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista;
import java.io.IOException;

public class ValorV {
    
    private InOut io;    

    public ValorV() {
        io = new InOut();
    }
    
    public void interactuar(ValorC controlador){
        assert controlador != null;
        try {
            new FloristeriaVista().mostrarValor(controlador.getValor());
            new YesWaiting("\nHa finalizado de ver el valor").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }
        controlador.seleccionarMenu(); 
    }
}
