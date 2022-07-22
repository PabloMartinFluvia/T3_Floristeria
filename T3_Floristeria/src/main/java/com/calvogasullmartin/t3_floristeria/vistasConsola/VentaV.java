package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.VentaC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.SeleccionadorV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV;
import java.io.IOException;

public class VentaV extends SeleccionadorV {

    private InOut io;

    private VentaC controlador;
    
    private IncrementosV incrV;

    public VentaV(VentaC controlador) {
        io = new InOut();
        this.controlador = controlador;
        controlador.resetTiquet();
        this.incrV = new IncrementosV(controlador);   
    }

    public void interactuar() {
        YesNoDialog requerimiento = new YesNoDialog("Quiere añadir más productos al tiquet");
        boolean ventaHecha = false;
        try {
            do {
                if (seleccionarIdProducto(controlador, "añadir al tiquet")) {
                    step2();
                } else {
                    //le ha dado a cancelar al elegir un id incorrecto
                }
                ventaHecha = requerimiento.read();
                if(ventaHecha){
                    controlador.guardarTiquet();
                    incrV.actualizarTotalesStock();
                }
            } while (!ventaHecha);
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV(controlador).isMore("crear más tiquets");
    }

    @Override
    protected void step2() throws IOException {
        String mensage = "el número de unidades vendidas: ";
        int maxCantidad = -controlador.getMinIncr();
        int cantidadVendida = new ProductoUnidadV().pedirIncremento(mensage,1, maxCantidad);
        controlador.addProductoUnidadEnTiquet(cantidadVendida);
    }
}
