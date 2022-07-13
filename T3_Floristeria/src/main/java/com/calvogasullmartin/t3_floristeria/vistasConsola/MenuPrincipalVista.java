package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import java.util.Scanner;

public class MenuPrincipalVista {

    private MenuControlador controlador;
    
    private InOut io;

    public MenuPrincipalVista() {
        io = new InOut();
    }

    public void interactuar(MenuControlador controlador) {
        assert controlador != null;
        this.controlador = controlador;
        mostrarMenu();
        int opcion = pedirOpcion();
        indicarFuncionalidad(opcion);
    }
    
    private void mostrarMenu(){
        String mensaje = "MENU INICAL:\n"
                + "\t1) Añadir un articulo.\n"
                + "\t2) Modificar unidades de un articulo.\n"
                + "\t3) Eliminar un articulo.\n"
                
                + "\t4) Ver los productos en stock.\n"
                
                + "\t5) Ver valor total del stock.\n"
                
                + "\t6) Realizar una venta.\n"
                + "\t7) Mostrar histórico de tickets.\n"
                + "\t8) Mostrar total ventas.\n\n"
                + "\t0) SALIR.\n";        
        io.writeln(mensaje);
        //System.out.println(mensaje);
    }
    
    private int pedirOpcion(){   
        
        //PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Selecciona Opcion: ", 0, 8);
        Scanner entrada = new Scanner(System.in);
                
        int opcion;
        boolean ok = false;
        do {
            //opcion = solicitud.read();
            System.out.println("Selecciona una opcion entre 0 i 8: ");
            opcion = entrada.nextInt();
            if (opcion >= 0 && opcion <= 8) { 
                ok = true;
            } else {
                io.writeln("Opcion no correcta. Debe ser entre 0 i 8");
                //System.out.println("Opcion no correcta. Debe ser entre 0 i 8");
            }
        } while (!ok);
        return opcion;
    }
    
    private void indicarFuncionalidad(int opcion){
        assert opcion >= 0 && opcion <= 8;
        switch (opcion) {
            case 1:
                controlador.introducirArticulo();
                break;
            case 2:
                controlador.modificarUnidades();
            case 3:
                controlador.retirarAriculo();
                break;
            case 4:
                controlador.mostrarStock();
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
