package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class MenuV {
    
    private InOut io;
    
    private MenuC controlador;
    
    private final int MIN_OPTION = 0;

    public MenuV(MenuC controlador) {
        assert controlador != null;
        io = new InOut();
        this.controlador = controlador;
    }

    public void interactuar() {        
        mostrarMenu();        
        int opcion = pedirOpcion();
        if(opcion != MIN_OPTION){
            controlador.canviarEstado(opcion);
        }else{
            controlador.selectExit();
        }
    }
    
    private void mostrarMenu(){
        io.writeln("\n"+controlador.getMenuMensage());
    }
    
    private int pedirOpcion(){
        String mensage = "Seleccione una opción";
        int max = controlador.getNumOptionsNoExit();
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado(mensage,MIN_OPTION,max);
        return solicitud.read();
    }        
}
