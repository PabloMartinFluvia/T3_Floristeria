package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementadorC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV;
import java.io.IOException;

public class IncrementadorV {
    
    private InOut io;
    
    private IncrementadorC controlador;

    public IncrementadorV(IncrementadorC controlador) {
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void requerirIncremento(String mensage){
        controlador.resetIncr();
        controlador.resetIncrValor();
        int max = controlador.getMaxIncr();
        int min = controlador.getMinIncr();        
        controlador.setIncr(new ProductoUnidadV().pedirIncremento(mensage, min, max));
        controlador.addIncrValor();
        controlador.addIncrValorStock();
    }
    
    public void actualizarTotalesStock() throws IOException{
        controlador.updateValorTotal();
        controlador.updateValorStock();
        io.writeln("Valor en stock actualizado con éxito.");
    }
}
