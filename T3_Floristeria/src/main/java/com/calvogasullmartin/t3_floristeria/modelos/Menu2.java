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
                + "\t1) Realizar una venta.\n"
                + "\t2) Ver los productos en stock(s).\n\n"
                
                + "\t3) Añadir un nuevo articulo.\n"
                + "\t4) Modificar unidades en stock de un articulo.\n"
                + "\t5) Descatalogar un articulo.\n\n"
                                                                                                              
                + "\t6) Ver el valor total del stock (actual).\n"  
                + "\t7) Ver el valor total de la facturación (actual).\n"
                + "\t8) Ver todos los tickets de ventas pasadas.\n\n"
                
                + "\t0) SALIR.\n"; 
    }

    public String getMenuMensage() {
        return menuMensage;
    }

    public int getNumOpcionesSinExit() {
        return numOpcionesSinExit;
    }    
}
