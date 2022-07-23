package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public class ProductoUnidadV2 {
    
    private InOut io;        

    public ProductoUnidadV2() {
        io = new InOut();
    }
    
    public int pedirIncremento(String mensage, int min, int max) {
        PedirEnteroLimitado requerimiento = new PedirEnteroLimitado("Introduzca "+mensage, min, max);
        return requerimiento.read();
    }
    
    public void mostrarProductoUnidad(ConjuntosC2 controlador, int[] indexs){
        //solo se muestran los no descatalogadods (en la relación producto-conjunto la cantidad es >= 0)           
        int cantidad = controlador.getCantidad(indexs);
        if (cantidad >= 0) {
            new ProductoCompletoV2().mostrarProducto(controlador, indexs);
            if (controlador.isConUnidades()) {
                mostrarCantidad(controlador.getMensageCantidad(), cantidad);
            } 
        }
    }
    
    private void mostrarCantidad(String mensage, int cantidad){
        io.writeln("\t\t"+"Cantidad "+mensage+": " + cantidad + " unidades\n");
    }
    
}
