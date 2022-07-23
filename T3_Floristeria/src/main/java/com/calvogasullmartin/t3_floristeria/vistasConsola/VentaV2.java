package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.SeleccionadorV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.ProductoUnidadV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.VentaC2;

public class VentaV2 extends SeleccionadorV2 {

    private InOut io;

    private VentaC2 controlador;
    
    private IncrementosV2 incrV;

    public VentaV2(VentaC2 controlador) {
        io = new InOut();
        this.controlador = controlador;
        this.controlador.resetTiquet();
        this.incrV = new IncrementosV2(controlador);   
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
                ventaHecha = !requerimiento.read();
                if(ventaHecha){
                    controlador.guardarTiquet();
                    io.write("Tiquet guardado con éxito");
                    incrV.actualizarTotalesStock();
                }
            } while (!ventaHecha);
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV2(controlador).isMore("crear más tiquets");
    }

    @Override
    protected void step2() throws IOException {
        String mensage = "el número de unidades vendidas: ";
        int maxCantidad = -controlador.getMinIncr();
        int cantidadVendida = new ProductoUnidadV2().pedirIncremento(mensage,1, maxCantidad);        
        controlador.addProductoUnidadEnTiquet(cantidadVendida);
        controlador.addIncrValor();
        controlador.addIncrValorStock();
        controlador.modificarCantidadEnStock();
    }
}
