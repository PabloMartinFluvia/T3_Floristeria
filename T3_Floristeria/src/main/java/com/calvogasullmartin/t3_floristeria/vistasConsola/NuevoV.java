package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoCompletoV;
import java.io.IOException;

public class NuevoV {

    private InOut io;

    public NuevoV() {
        io = new InOut();
    }
    
    public void interactuar(NuevoC controlador) {
        assert controlador != null;    
        controlador.resetProductoUnidad();
        new ProductoCompletoV().requerirNuevoProducto(controlador);
        try {
            if (controlador.estaEnStock()) { 
                
            }else{
               io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características."); 
            }                
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        
        
        try {
            if (controlador.isNuevo()) {                
                new ProductoUnidadVista().pedirUnidadesIniciales(controlador);
                controlador.addProductoConUnidadesEnStock();
                io.writeln("Producto añadido con éxito.");
                controlador.incrementarValores();
                io.writeln("Valor del stock actualizado con éxito.");
            } else {
                io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características.");
            }
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        repetir("añadir más articulos", controlador); 
    }
}
