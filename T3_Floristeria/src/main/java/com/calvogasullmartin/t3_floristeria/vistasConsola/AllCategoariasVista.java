package com.calvogasullmartin.t3_floristeria.vistasConsola;

public abstract class AllCategoariasVista extends CategoriaVista{

    @Override
    protected int ofrecerOpcionTodos() {
        String mensage = "\n\t0) TODOS";
        io.writeln(mensage);
        return 0;
    }
    
}
