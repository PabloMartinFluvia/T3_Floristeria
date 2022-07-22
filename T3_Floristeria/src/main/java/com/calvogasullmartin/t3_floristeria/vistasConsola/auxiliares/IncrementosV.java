package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementosC;

public class IncrementosV {
    
    private InOut io;
    
    private IncrementosC controlador;

    public IncrementosV(IncrementosC controlador) {
        io = new InOut();
        this.controlador = controlador;      
        resetAll();
    }
    
    private void resetAll(){
        this.controlador.resetIncr();
        this.controlador.resetIncrValor();
        this.controlador.resetIncrValorStocks();
    }
    
    public void requerirIncremento(String mensage){            
        int max = controlador.getMaxIncr();
        int min = controlador.getMinIncr();        
        controlador.setIncr(new ProductoUnidadV().pedirIncremento(mensage, min, max));
        controlador.addIncrValor();
        controlador.addIncrValorStock();
    }
    
    public void actualizarTotalesStock() throws IOException{
        controlador.updateValorTotal();
        controlador.updateValoresStock();
        io.writeln("Valor en stock actualizado con éxito.");
        controlador.setIncrsNull();
    }        
}
