package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.controladores.PersistenciaC;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;

public class EndV {
    private final InOut io;
    
    private final PersistenciaC controlador;
    
    public EndV(PersistenciaC controlador) {
        io = new InOut();    
        this.controlador = controlador;
    }
    
    public void manageError(Exception ex){
        io.writeln(controlador.getErrorBD());
        controlador.selectExit();
        ex.printStackTrace();
    }
    
    public void setEstadoMenu(){
        controlador.selectMenu();
    }   
    
    public void waitBeforeToMenu(){
        io.readString("Apriete Enter para volver al menú.");        
        setEstadoMenu();
    }
    
    public void askRepeatAction(String mensage){
        YesNoDialog requerimiento = new YesNoDialog("Desea "+mensage+" o volver al menú");        
        if (!requerimiento.read()) {
            setEstadoMenu();
        }
    }
}
