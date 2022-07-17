package com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos;

import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import java.io.IOException;
import java.util.Scanner;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.StocksController;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoControlador;

public class ProductoUnidadVista {

    private InOut io;

    public ProductoUnidadVista() { 
       io = new InOut();
    }

    public void pedirUnidadesIniciales(NuevoProductoControlador controlador) {
        String mensaje = "Introduce la cantidad inicial de este producto que habrá en el stock (>0): ";
        PedirEnteroLimitado requerimiento = new PedirEnteroLimitado(mensaje, controlador.getMaxCantidad());
        controlador.almacenarUnidadesIniciales(requerimiento.read());
    }

    public void muestraProductoUnidadInStocks(StocksController controlador, int stockIndex, int productoIndex) {
        int cantidad = controlador.getCantidadProductoInStock(stockIndex, productoIndex);
        new ProductoCompletoVista().muestraProductoCompletoInStocks(controlador, stockIndex, productoIndex);
        io.writeln("\t\t"+"Cantidad actual en stock: " + cantidad + " unidades\n");
    }
    
    
    
    
    
    

    private Z_OLD_ModificarProductoControlador modificarControlador;

    public void interactuar (Z_OLD_ModificarProductoControlador modificarControlador) {
        this.modificarControlador = modificarControlador;
        boolean exit = false;
        do {
            int producto_id = obtenerIdProducto();
            if (modificarControlador.isProductoIdValid(producto_id)) {
                if (!modificarControlador.IsUpdateUnits()) {
                    eliminarProducto();
                } else {
                    actualizarUnidades();
                }
                exit = true;
            } else {
                //se le podria dar la opción de "cancelar" y volver al menú. De momento NO implementar.
            }
        } while (!exit);
        modificarControlador.seleccionarMenu();
    }

    ////substituirlo por la metodologia de las linias 17-19
    private int pedrirIntCantidad(int min) {
        Scanner sc = new Scanner(System.in);
        int input;
        boolean ok = false;
        do {
            input = sc.nextInt();
            if (input >= min) {
                ok = true;
            } else {
                System.out.println("Error. Introduce una cantidad superior a " + min);
            }
        } while (!ok);
        return input;
    }

    private void eliminarProducto() {
        boolean eliminar = isCorrecto("Estas seguro de querer eliminar el producto?\n"
                + "\t1) Si.\n"
                + "\t0) No.\n");
        if (eliminar) {
            try {
                modificarControlador.eliminarProductoUnidad();
                modificarControlador.disminuirValoresStock();
            } catch (IOException ex) {
                System.out.println("Base de datos inaxcesible.");
            }
        }//en el caso contrario no hace nada -> se continua en el metodo interactuar + se sale del bucle + se va al estado en menu
    }

    private boolean isCorrecto(String mensaje) {
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.println(mensaje);
            input = sc.nextInt();
        } while (input < 0 || input > 1);
        boolean ok = true;
        if (input == 0) {
            ok = false;
        }
        return ok;
    }

    private void actualizarUnidades() {
        int cantidadPrevia = modificarControlador.getUnidadesActuales();
        System.out.println("En cuantas unidades variamos el stock?");
        int incremento = pedrirIntCantidad(cantidadPrevia * -1);
        boolean modificar = isCorrecto("Estas seguro de querer modificar las unidades en " + incremento + "?\n"
                + "\t1) Si.\n"
                + "\t0) No.\n");
        if (modificar) {
            try {
                modificarControlador.incrementarProductoUnidad(incremento);
                modificarControlador.actualizarValoresStock(incremento);
            } catch (IOException ex) {
                System.out.println("Base de datos inaxcesible.");
            }
        }//en el caso contrario no hace nada -> se continua en el metodo interactuar + se sale del bucle + se va al estado en menu
    }

    private int getInt(String mensaje, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.println(mensaje);
            input = sc.nextInt();
        } while (input < min || input > max);
        return input;
    }

    private int obtenerIdProducto() {
        String mensaje = "Introduce el numero de identificación del producto: ";
        System.out.println(mensaje);
        int producto_id = pedrirIntCantidad(0);
        return producto_id;
    }

}
