package com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.UnidadesC;

public class UnidadesV {
    
    private InOut io;
    
    private UnidadesC controlador;

    public UnidadesV(UnidadesC controlador) {
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void requerirIncremento(String mensage){
        controlador.resetIncr();
        controlador.resetIncrValor();
        controlador.resetIncrValorStocks();
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
