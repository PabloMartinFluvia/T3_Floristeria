package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class MenuV {
    
    private InOut io;
    
    private MenuC controlador;

    public MenuV(MenuC controlador) {
        assert controlador != null;
        io = new InOut();
        this.controlador = controlador;
    }

    public void interact() {        
        io.writeln("\n"+controlador.getMenuMessage());
        controlador.changeState(askMenuOption()); //el num de la opcion del menú = index en enum estados
    }
    
    private int askMenuOption(){
        String menssage = "Seleccione una opción";
        int min = controlador.getRangeMenuOptions()[0];
        int max = controlador.getRangeMenuOptions()[1];
        PedirEnteroLimitado requirement = new PedirEnteroLimitado(menssage,min,max);
        return requirement.read();
    }        
}
