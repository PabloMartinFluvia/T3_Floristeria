package com.calvogasullmartin.t3_floristeria.vistasConsola;

public abstract class TodasOpcionCategoriaVista extends OpcionCategoriaVista{

    @Override
    protected int isSoloUnaOpcion() {
        String mensage = "\n\t0) TODOS";
        io.writeln(mensage);
        return 0;
    }
    
}
