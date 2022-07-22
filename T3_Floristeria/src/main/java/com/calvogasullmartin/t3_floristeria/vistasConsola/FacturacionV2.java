package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaVista2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC2;

public class FacturacionV2 {
    
    private InOut2 io;        

    public FacturacionV2() {
        io = new InOut2();
    }
    
    public void interactuar(FacturacionC2 controlador){
        assert controlador != null;
        try {
            new FloristeriaVista2().mostrarFacturacion(controlador.getFacturacion());
            new YesWaiting2("\nHa finalizado de ver la facturación").bucleYes();            
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }
        controlador.seleccionarMenu(); 
    }
}
