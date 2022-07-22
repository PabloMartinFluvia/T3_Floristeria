package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public class ProductoUnidadV2 {
    
    private InOut2 io;        

    public ProductoUnidadV2() {
        io = new InOut2();
    }
    
    public int pedirIncremento(String mensage, int min, int max) {
        PedirEnteroLimitado2 requerimiento = new PedirEnteroLimitado2("Introduzca "+mensage, min, max);
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
