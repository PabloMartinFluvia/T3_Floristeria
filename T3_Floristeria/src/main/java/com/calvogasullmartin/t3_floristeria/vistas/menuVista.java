package com.calvogasullmartin.t3_floristeria.vistas;

public class MenuVista {
    
    /*
    public static void mostrarMenu() {
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
    */
    
    /*
    public static int seleccionaOpcion(int opciones) {
        int opcion;
        InOut io = new InOut();
        boolean ok = false;
        do {
            opcion = io.readInt("Selecciona Opcion: ");
            if (opcion >= 0 && opcion <= opciones) {
                ok = true;
            } else {
                io.writeln("Opcion no correcta. Debe ser entre 0 i " + opciones);
            }
        } while (!ok);
        return opcion;
    }
    */
    
    /*
    public static void realizarTarea(Integer opcion, Floristeria floristeria) {
        switch (opcion) {
            case 1:
                articuloVista(floristeria);
                break;
            case 2:
                retirarArticuloVista(floristeria);
                break;
            case 3:
                mostrarStockVista(floristeria);
                break;
            case 4:
                mostrarStockUnitVista(floristeria);
                break;
            case 5:
                valorFloristeriaVista(floristeria);
                break;
            case 6:
                ventaVista(floristeria);
                break;
            case 7:
                historialTicketsVista(floristeria);
                break;
            case 8:
                totalVentasVista(floristeria);
                break;
            case 0:
                //return
                break;
        }
    }
    */
}
