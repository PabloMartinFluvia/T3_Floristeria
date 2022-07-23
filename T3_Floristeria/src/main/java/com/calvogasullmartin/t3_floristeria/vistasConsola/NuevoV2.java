package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoCompletoV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC2;

public class NuevoV2 {

    private InOut io;
    
    private NuevoC2 controlador;

    public NuevoV2(NuevoC2 controlador) {
        io = new InOut();
        this.controlador = controlador;
    }

    public void interactuar() {
        assert controlador != null;        
        new ProductoCompletoV2().requerirNuevoProducto(controlador);
        try {
            if (!controlador.estaEnStock()) {
                add();
            } else {
                io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características.");
            }
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV2(controlador).isMore("añadir más articulos");
    }

    private void add() throws IOException {
        IncrementosV2 incrV = new IncrementosV2(controlador);  
        incrV.requerirIncremento("la cantidad inicial en stock");
        controlador.guardarNuevoProductoUnidad();
        io.writeln("Producto añadido con éxito.");
        incrV.actualizarTotalesStock();        
    }
}
