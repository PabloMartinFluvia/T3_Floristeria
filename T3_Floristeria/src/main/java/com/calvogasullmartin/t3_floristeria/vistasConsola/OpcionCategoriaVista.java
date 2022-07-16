package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public abstract class OpcionCategoriaVista {
    protected InOut io = new InOut();
    
    //devuelve indice en el enum
    public int pedirCategoria(String mensageOpcion){
        int numOpciones = Categoria.values().length;
        io.writeln("Opciones: ");
        for (int i = 0; i<numOpciones; i++){
            String categoria = "\t"+(i+1)+") "+Categoria.values()[i];
            io.writeln(categoria);
        }        
        int min = this.isSoloUnaOpcion();        
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Seleccione "+mensageOpcion ,min, numOpciones);
        int opcion = solicitud.read();
        return opcion-1;
    }
    
    protected abstract int isSoloUnaOpcion();
}
