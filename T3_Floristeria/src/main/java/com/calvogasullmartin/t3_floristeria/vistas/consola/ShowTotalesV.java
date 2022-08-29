package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.FloristeriaV;
import com.calvogasullmartin.t3_floristeria.controladores.ShowValorC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowFacturacionC;
import java.io.IOException;

public class ShowTotalesV {

    private final FloristeriaV floristeriaV;
    
    private EndV endV;

    public ShowTotalesV() {
        floristeriaV = new FloristeriaV();
    }
    
    public void interact(ShowValorC controlador){
        assert controlador != null;
        endV = new EndV(controlador);
        try {
            floristeriaV.printValor(controlador.getValor());  
            endV.waitBeforeToMenu();
        } catch (IOException ex) {
            endV.manageError();
        }           
    }
    
    public void interact(ShowFacturacionC controlador){
        assert controlador != null;
        endV = new EndV(controlador);
        try {
            floristeriaV.printFacturacion(controlador.getFacturacion());  
            endV.waitBeforeToMenu();
        } catch (IOException ex) {
            endV.manageError();           
        }        
    }
}
