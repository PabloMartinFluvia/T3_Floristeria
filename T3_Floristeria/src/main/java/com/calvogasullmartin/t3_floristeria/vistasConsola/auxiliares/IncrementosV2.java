package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementosC2;

public class IncrementosV2 {
    
    private InOut io;
    
    private IncrementosC2 controlador;

    public IncrementosV2(IncrementosC2 controlador) {
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
        controlador.setIncr(new ProductoUnidadV2().pedirIncremento(mensage, min, max));
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
