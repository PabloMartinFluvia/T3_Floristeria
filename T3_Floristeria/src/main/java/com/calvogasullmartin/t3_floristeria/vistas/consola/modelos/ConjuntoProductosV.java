package com.calvogasullmartin.t3_floristeria.vistas.consola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.ShowConjuntosC;

public class ConjuntoProductosV {

    private InOut io;

    public ConjuntoProductosV() {
        io = new InOut();
    }

    public void mostrarConjuntos(ShowConjuntosC controlador) {    
        assert controlador != null;
        int numConjuntosToShow = controlador.getNumConjuntosToShow();        
        if (numConjuntosToShow == 0) {
            io.writeln("No hay tiquets registrados."); 
        } else {
            for (int i = 0; i < numConjuntosToShow; i++) {
                controlador.setConjuntoToShow(i);
                io.writeln();
                mostrarConjunto(controlador);
            }
        }
        io.writeln();
    }

    private void mostrarConjunto(ShowConjuntosC controlador) {
        String titulo = controlador.getTitle();
        io.writeln(titulo);
        float value = controlador.getValue();
        io.writeln("El valor total es de: " + value);
        int numProductos = controlador.getNumProductosToShow();
        if (numProductos == 0) {
            io.writeln("\tActualmente no hay productos en este stock.");
        } else {            
            ProductoUnidadV productoUnidadV = new ProductoUnidadV();
            for (int indexProducto = 0; indexProducto < numProductos; indexProducto++) {
                productoUnidadV.mostrarProductoUnidad(controlador, indexProducto);
            }
        }
    }
}
