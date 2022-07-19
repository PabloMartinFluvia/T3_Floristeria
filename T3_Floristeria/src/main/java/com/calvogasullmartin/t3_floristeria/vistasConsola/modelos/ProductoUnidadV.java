package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class ProductoUnidadV {
    
    private InOut io;        

    public ProductoUnidadV() {
        io = new InOut();
    }
    
    public int pedirIncremento(String mensage, int min, int max) {
        PedirEnteroLimitado requerimiento = new PedirEnteroLimitado("Introduzca ", min, max);
        return requerimiento.read();
    }
}
