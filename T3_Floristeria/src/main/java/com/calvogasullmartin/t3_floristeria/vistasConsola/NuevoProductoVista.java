package com.calvogasullmartin.t3_floristeria.vistasConsola;

//no hay problema en que una vista esté acoplada a Categoria, ya que es un enum del modelo.
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.ProductoUnidadVista;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.ProductoCompletoVista;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoControlador;

public class NuevoProductoVista {

    private InOut io;

    public NuevoProductoVista() {
        io = new InOut();
    }

    public void interactuar(NuevoProductoControlador controlador) {
        assert controlador != null;
        controlador.inicializarNuevoProducto();
        new ProductoCompletoVista().requerirNuevoProducto(controlador);
        try {
            if (controlador.isNuevo()) {
                new ProductoUnidadVista().pedirUnidadesIniciales(controlador);
                controlador.addProductoConUnidadesEnStock();
                io.writeln("Producto añadido con éxito.");
                controlador.actualizarValoresStock();
                io.writeln("Valor del stock actualizado con éxito.");
            } else {
                io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características.");
            }
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        finalizar(controlador);
    }
    
    private void finalizar(NuevoProductoControlador controlador){
        YesNoDialog requerimiento = new YesNoDialog("Desea añadir más articulos");
        if (!requerimiento.read()) {
            controlador.seleccionarMenu();
        }
    }
}
