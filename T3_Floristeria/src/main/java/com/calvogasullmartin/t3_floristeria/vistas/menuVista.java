package com.calvogasullmartin.t3_floristeria.vistas;

public class MenuVista {
    MenuControlador controlador;


    public MenuVista(){}

    private MenuControlador getMenuControlador() throws IOException{
        return this.Controlador;
    }
    
    public void setControlador(MenuControlador controlador){//codigo repetido
        this.controlador = controlador;
    }

    public void interactuar(menuControlador controlador){
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
        int opcion;
        boolean ok = false;
        do {
            opcion = io.readInt("Selecciona Opcion: ");
            if (opcion >= 0 && opcion <= opciones) {
                ok = true;
            } else {
                io.writeln("Opcion no correcta. Debe ser entre 0 i " + opciones);
            }
        } while (!ok);

        switch (opcion) {
            case 1:
                controlador.introducirArticulo();
                break;
            case 2:
                controlador.retirarAriculo();
                break;
            case 3:
                controlador.mostrarStock();
                break;
            case 4:
                controlador.mostrarStockUnitat();
                break;
            case 5:
                controlador.valorFloristeria();
                break;
            case 6:
                controlador.venta();
                break;
            case 7:
                controlador.historialVenta();
                break;
            case 8:
                controlador.totalVentas();
                break;
            case 0:
                controlador.exit();
                break;
        }
    }

}

