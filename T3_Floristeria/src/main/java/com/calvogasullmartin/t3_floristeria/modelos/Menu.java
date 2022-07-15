package com.calvogasullmartin.t3_floristeria.modelos;

public class Menu {
    
    private String menuMensage;
    
    private int numOpcionesSinExit;
    
    public Menu(){
        iniciarMensage();
        numOpcionesSinExit = 8;
    }
    
    public final void iniciarMensage(){
        menuMensage = "MENU INICAL:\n"
                + "\t1) Añadir un articulo.\n"
                + "\t2) Modificar unidades de un articulo.\n"
                + "\t3) Eliminar un articulo.\n"
                
                + "\t4) Ver los productos en stock.\n"
                
                + "\t5) Ver valor total del stock.\n"
                
                + "\t6) Realizar una venta.\n"
                + "\t7) Mostrar histórico de tickets.\n"
                + "\t8) Mostrar total ventas.\n\n"
                + "\t0) SALIR.\n"; 
    }

    public String getMenuMensage() {
        return menuMensage;
    }

    public int getNumOpcionesSinExit() {
        return numOpcionesSinExit;
    }    
}
