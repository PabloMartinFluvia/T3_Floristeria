package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.DescatalogarProductoC;

public class DescataogarProductoV extends ElegirProductoV {

    private final DescatalogarProductoC controlador;

    public DescataogarProductoV(DescatalogarProductoC controlador) {
        super(controlador);
        assert controlador != null;
        this.controlador = controlador;
    }

    public void interact() {
        try {
            update(controlador, "descatalogar");
            endV.askRepeatAction("descatalogar más articulos");
        } catch (IOException ex) {
            endV.manageError();
        }        
    }

    @Override
    protected void updateProduct() throws IOException{   
        int productoId = controlador.getProductoId();
        int cantidadActual = -controlador.getIncrementRange()[0];
        String messageDelete = "Confirme que quiere eliminar del stock el "
                + "producto con id "+ productoId+" y sus "+cantidadActual+" unidades.";
        YesNoDialog requirement = new YesNoDialog(messageDelete);        
        if (requirement.read()) {              
            controlador.updateUnitsStock(-cantidadActual);
            controlador.updateTotalsValues();
            io.writeln("Producto descatalogado con éxito.");
            controlador.resetProductUpdated();
        }
    }
}
