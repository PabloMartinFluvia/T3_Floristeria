package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.NuevoC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.UnidadesV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoCompletoV;
import java.io.IOException;

public class NuevoV {

    private InOut io;
    
    private NuevoC controlador;

    public NuevoV(NuevoC controlador) {
        io = new InOut();
        this.controlador = controlador;
    }

    public void interactuar() {
        assert controlador != null;        
        new ProductoCompletoV().requerirNuevoProducto(controlador);
        try {
            if (!controlador.estaEnStock()) {
                add();
            } else {
                io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características.");
            }
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        new ToMenuV(controlador).isMore("añadir más articulos");
    }

    private void add() throws IOException {
        UnidadesV incrV = new UnidadesV(controlador);        
        incrV.requerirIncremento("la cantidad inicial en stock");
        controlador.guardarNuevoProductoUnidad();
        io.writeln("Producto añadido con éxito.");
        incrV.actualizarTotalesStock();
        controlador.setModelsNull();
    }
}
