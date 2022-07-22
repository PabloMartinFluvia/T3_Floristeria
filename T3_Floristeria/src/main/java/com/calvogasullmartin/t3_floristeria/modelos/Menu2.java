package com.calvogasullmartin.t3_floristeria.modelos;

public class Menu2 {
    
    private String menuMensage;
    
    private final int numOpcionesSinExit;
    
    public Menu2(){
        iniciarMensage();
        numOpcionesSinExit = 8;
    }
    
    private void iniciarMensage(){
        menuMensage = "MENU INICAL:\n"
                + "\t1) Añadir un articulo.\n"
                + "\t2) Modificar unidades de un articulo.\n"
                + "\t3) Descatalogar un articulo.\n"
                
                + "\t4) Ver los productos en stock(s).\n"
                
                + "\t5) Ver valor total del stock.\n"
                
                + "\t6) Realizar una venta.\n"
                + "\t7) Ver los tickets de ventas pasadas.\n"
                + "\t8) Mostrar el total de todas ventas.\n\n"
                + "\t0) SALIR.\n"; 
    }

    public String getMenuMensage() {
        return menuMensage;
    }

    public int getNumOpcionesSinExit() {
        return numOpcionesSinExit;
    }    
}
