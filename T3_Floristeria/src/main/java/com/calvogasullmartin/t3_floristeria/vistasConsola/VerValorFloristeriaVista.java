package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.VerValorFloristeriaController;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.RepetirFuncionalidadVista;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.FloristeriaVista;
import java.io.IOException;

public class VerValorFloristeriaVista extends RepetirFuncionalidadVista{
    
    public void interactuar (VerValorFloristeriaController controlador){
        try {
            new FloristeriaVista().mostrarValorFloristeria("de los stocks", controlador.getTotalStocks());
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        repetir("volver a mostrar el valor", controlador);
    }
}
