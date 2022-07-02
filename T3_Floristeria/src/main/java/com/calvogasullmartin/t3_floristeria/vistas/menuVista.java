package com.calvogasullmartin.t3_floristeria.vistas;

public class menuVista {

    public static void mostrarMenu(){
       String mensaje = "MENU INICAL:\n"
            + "\t1) Añadir articulo.\n"
            + "\t2) Eliminar articulo.\n"
            + "\t3) Mostrar lista del stock.\n"
            + "\t4) Mostrar stock con unidades.\n"
            + "\t5) Mostrar valor total del stock.\n"
            + "\t6) Realizar venta.\n"
            + "\t7) Mostrar tickets.\n"
            + "\t8) Mostrar total ventas.\n\n"
            + "\t0) SALIR.\n";   

        InOut io = new InOut();
        io.writeln(mensaje); 
    }
}
