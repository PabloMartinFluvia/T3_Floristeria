package com.calvogasullmartin.t3_floristeria.vistas.consola.modelos;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class CategoriaV {

    private final InOut io = new InOut();

    private final int maxOptions;

    public CategoriaV() {
        maxOptions = Categoria.values().length;
    }

    //devuelve indice en el enum (si la opción de Todos está disponible y se selecciona devuelce <0)
    public int askForCategoriaIndex(boolean allOptionsPosible, String optionMessage) {  
        assert optionMessage != null;
        offerOptions();
        return requireOption(allOptionsPosible, optionMessage) - 1;
    }

    private void offerOptions() {
        io.writeln("\nOpciones: ");
        for (int i = 0; i < maxOptions; i++) {
            String message = "\t" + (i + 1) + ") " + Categoria.values()[i];
            io.writeln(message);
        }
    }

    private int requireOption(boolean allOptionsPosible, String optionMessage) {
        int minOptions;
        if (allOptionsPosible) {
            io.writeln("\n\t0) TOD@S");
            minOptions = 0;
        } else {
            minOptions = 1;
        }
        PedirEnteroLimitado requirement = new PedirEnteroLimitado
            ("Seleccione " + optionMessage, minOptions, maxOptions);
        return requirement.read();
    }
}
