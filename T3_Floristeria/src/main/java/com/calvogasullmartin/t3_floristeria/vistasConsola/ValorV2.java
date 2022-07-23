package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC2;

public class ValorV2 {
    
    private InOut io;    

    public ValorV2() {
        io = new InOut();
    }
    
    public void interactuar(ValorC2 controlador){
        assert controlador != null;
        try {
            new FloristeriaVista2().mostrarValor(controlador.getValor());
            new YesWaiting("\nHa finalizado de ver el valor").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }
        controlador.seleccionarMenu(); 
    }
}
