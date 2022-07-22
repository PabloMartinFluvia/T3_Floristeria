package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public class ConjuntoProductosV2 {

    private InOut2 io;

    private ConjuntosC2 controlador;

    public ConjuntoProductosV2(ConjuntosC2 controlador) {
        io = new InOut2();
        this.controlador = controlador;
    }

    public void mostrarConjuntos() {
        int numConjuntosToShow = controlador.getNumConjuntosToShow();
        if (numConjuntosToShow == 0) {
           io.writeln("No hay tiquets registrados."); 
        } else {
            for (int i = 0; i < numConjuntosToShow; i++) {
                mostrarConjunto(i);
            }
        }
    }

    private void mostrarConjunto(int indexConjunto) {
        String titulo = controlador.getTitle(indexConjunto);
        io.writeln("\n" + titulo);
        float value = controlador.getValue(indexConjunto);
        io.writeln("El valor total es de: " + value);
        int[] indexs = new int[2];
        indexs[0] = indexConjunto;
        int numProductos = controlador.getNumProductosToShow(indexConjunto);
        if (numProductos == 0) {
            io.writeln("No hay productos registrados.");
        } else {
            ProductoUnidadV2 productoUnidadV = new ProductoUnidadV2();
            for (int indexProducto = 0; indexProducto < numProductos; indexProducto++) {
                indexs[1] = indexProducto;
                productoUnidadV.mostrarProductoUnidad(controlador, indexs);
            }
        }
        io.writeln();
    }
}
