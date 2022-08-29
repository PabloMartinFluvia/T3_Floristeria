package com.calvogasullmartin.t3_floristeria.vistas.consola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import com.calvogasullmartin.t3_floristeria.controladores.ShowConjuntosC;

public class ProductoUnidadV {

    private InOut io;

    public ProductoUnidadV() {
        io = new InOut();
    }

    public void mostrarProductoUnidad(ShowConjuntosC controlador, int indexProducto) {
        assert controlador != null;
        assert indexProducto >=0 && indexProducto < controlador.getNumProductosToShow();
        new ProductoCompletoV().mostrarProducto(controlador, indexProducto);
        if (controlador.isWithUnits()) {
            String mensage = controlador.getMensageCantidad();
            int cantidad = controlador.getCantidad(indexProducto);
            io.writeln("\t\t" + "Cantidad " + mensage + ": " + cantidad + " unidades");
        }
    }
                    
    public int askIncrement(String message, int min, int max) {
        assert message != null;
        assert max >= min;
        PedirEnteroLimitado require = new PedirEnteroLimitado("Introduzca " + message, min, max);
        return require.read();
    }    
}
