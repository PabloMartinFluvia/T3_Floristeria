package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoUnidadVista {

    private AddProductoControlador addControlador;

    private ModificarProductoControlador modificarControlador;

    public ProductoUnidadVista() {
    }

    public void interactuar(AddProductoControlador controlador) {
        this.addControlador = controlador;
        enviarToContoladorUnidadesIniciales();
    }

    public void interactuar(ModificarProductoControlador modificarControlador) {
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

    private void eliminarProducto() {
        boolean eliminar = true;
        /*
        pedir confirmacion para eliminar el producto y guardarlo en la variable
         */
        if (eliminar) {
            try {                
                modificarControlador.eliminarProductoUnidad();
                modificarControlador.disminuirValoresStock();
            } catch (IOException ex) {
                /*
                dar mensaje de error que no se ha podido acceder a la BBDD
                 */
            }
        }//en el caso contrario no hace nada -> se continua en el metodo interactuar + se sale del bucle + se va al estado en menu
    }

    private void actualizarUnidades() {
        int cantidadPrevia = modificarControlador.getUnidadesActuales();
        int incremento = 0;
        /*
         *Pedir el incremento y guardarlo en la variable,
           el límite inferior és: -cantidadPrevia, ya que no se pueden tener unidades negativas
         */
        boolean modificar = true;
        /*
            Pedir confirmación para modificar las unidades
         */
        if (modificar) {
            try {
                modificarControlador.incrementarProductoUnidad(incremento);
                modificarControlador.actualizarValoresStock(incremento);                
            } catch (IOException ex) {
               /*
                dar mensaje de error que no se ha podido acceder a la BBDD
                 */
            }
        }//en el caso contrario no hace nada -> se continua en el metodo interactuar + se sale del bucle + se va al estado en menu
    }

    private int obtenerIdProducto() {
        int producto_id = 0;
        /*
        pedir al usuario que introduzca un id de algun producto que está en el stock que se acava de mostrar        
         */
        return producto_id;
    }

    private void enviarToContoladorUnidadesIniciales() {
        addControlador.almacenarUnidadesIniciales(pedirUnidadesIniciales());
    }

    private int pedirUnidadesIniciales() {
        int unidades = 0; // quitar asignacion de valor al implementar el método
        /*
        preguntar cuantas unidades iniciales tendrá este nuevo producto
        Se le puede dar la opción que por defecto sea una
         */
        return unidades;
    }
}
