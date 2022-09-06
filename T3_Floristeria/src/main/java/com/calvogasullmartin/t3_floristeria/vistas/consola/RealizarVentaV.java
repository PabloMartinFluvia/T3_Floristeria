package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.ProductoUnidadV;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.RealizarVentaC;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class RealizarVentaV extends ElegirProductoV {

    private final RealizarVentaC controlador;

    public RealizarVentaV(RealizarVentaC controlador) {
        super(controlador);
        assert controlador != null;
        this.controlador = controlador;
    }

    public void interact() {
        try {
            YesNoDialog requerimiento = new YesNoDialog("Quiere añadir más productos al tiquet");
            do {
                update(controlador, "añadir al tiquet");
            } while (requerimiento.read());
            finalizar();
            endV.askRepeatAction("crear más tiquets");
        } catch (IOException | SQLException | MongoException ex) {
            endV.manageError(ex);
        }
    }

    @Override
    protected void updateProduct() throws IOException, SQLException, MongoException {
        int minVenta = controlador.getMinVenta();
        int maxVenta = -controlador.getIncrementRange()[0];
        if (maxVenta >= minVenta) {
            String mensage = "las unidades vendidas de este producto";
            int cantidadVendida = new ProductoUnidadV().askIncrement(mensage, minVenta, maxVenta);
            controlador.updateUnitsStock(-cantidadVendida);
        }else{
            io.writeln("Error. No hay stock actualmente de este producto.");
        }
    }

    private void finalizar() throws IOException, SQLException, MongoException {
        if (controlador.isTicketValid()) {
            controlador.createTicket();
            controlador.updateTotalsValues();
            io.writeln("Ticket guardado con éxito.");
            controlador.resetProductUpdated();
            controlador.resetTicket();
        }
    }
}
