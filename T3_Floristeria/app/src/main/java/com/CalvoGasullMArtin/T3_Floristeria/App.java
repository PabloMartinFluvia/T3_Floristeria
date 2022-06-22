package com.CalvoGasullMArtin.T3_Floristeria;

import com.CalvoGasullMArtin.T3_Floristeria.controladores.crearFloristeriaController;

public class App {

    public static void main(String[] args) {
        new App().init();
    }

    public void init() {
        Integer opcion = null;
        Menu menu = new Menu();
        do {            
            menu.mostrarMenu();
            opcion = menu.seleccionaOpcion();              
        } while (opcion != 0);
    }
    
    public void realizarTarea(Integer opcion){
        switch(opcion) {
            case 1:new crearFloristeriaController();break;
            case 2:;break;
            case 3:;break;
            case 4:;break;
            case 5:;break;
            case 6:;break;
            case 7:;break;
            case 8:;break;
            case 9:;break;
            case 0:;break;
        }        
    }
}
