package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.FacturacionC;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.FloristeriaV;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;

public class TotalesV {
    
    private InOut io;    

    private FloristeriaV floristeriaV;

    public TotalesV() {
        io = new InOut();
        floristeriaV = new FloristeriaV();
    }
    
    public void interactuar(ValorC controlador){
        assert controlador != null;
        try {
            floristeriaV.printValor(controlador.getValor());
            wait("el valor");        
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }        
        controlador.selectMenu();
    }
    
    public void interactuar(FacturacionC controlador){
        assert controlador != null;
        try {
            floristeriaV.printFacturacion(controlador.getFacturacion());
            wait("la facturación");        
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());            
        }
        controlador.selectMenu(); 
    }
    
    private void wait(String mensage){
        new YesWaiting("\nHa finalizado de ver "+mensage).bucleYes();
    }
}
