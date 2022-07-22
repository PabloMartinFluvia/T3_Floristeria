package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.EliminarC2;
import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.SeleccionadorV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV2;
import java.io.IOException;

public class EliminarV2 extends SeleccionadorV2 {

    private InOut2 io;

    private EliminarC2 controlador;

    private IncrementosV2 incrV;

    public EliminarV2(EliminarC2 controlador) {
        io = new InOut2();
        this.controlador = controlador;
        this.incrV = new IncrementosV2(controlador);
    }

    public void interactuar() {
        try {
            if (seleccionarIdProducto(controlador, "descatalogar")) {
                step2();
            } else {
                //le ha dado a cancelar al elegir un id incorrecto
            }
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV2(controlador).isMore("descatalogar más artículos");
    }

    @Override
    protected void step2() throws IOException {
        
        controlador.modificarCantidadEnStock();
        controlador.addIncrValor();
        controlador.addIncrValorStock();
        controlador.modificarCantidadEnStock();

        controlador.eliminarProducto();
        io.write("Producto eliminadto con con éxito");
        incrV.actualizarTotalesStock();
    }
}
