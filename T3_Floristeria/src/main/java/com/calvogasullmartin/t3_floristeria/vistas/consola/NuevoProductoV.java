package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.ProductoCompletoV;
import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.ProductoUnidadV;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class NuevoProductoV {

    private final NuevoProductoC controlador;

    private final InOut io;

    public NuevoProductoV(NuevoProductoC controlador) {
        assert controlador != null;
        this.controlador = controlador;
        io = new InOut();
    }

    public void interact() {
        new ProductoCompletoV().askForNewProduct(controlador);
        EndV endV = new EndV(controlador);
        try {
            verifyNewProductOk();            
            endV.askRepeatAction("añadir más articulos");
            controlador.resetProductUpdated();
        } catch (IOException | SQLException | MongoException ex) {
            endV.manageError(ex);
        }
    }

    private void verifyNewProductOk() throws IOException, SQLException, MongoException {
        controlador.lookForIdemProduct();
        if (!controlador.isIdemProductAlreadyInStocks()) {
            if (controlador.isIdemProductSoldAnytime()) {
                askHowManageOldProduct();
            } else {
                controlador.setIsNew(true);
            }
            saveProduct();
        } else {
            io.writeln("ERROR: YA EXISTE EN STOCK un producto con idénticas características.");
        }
    }

    private void askHowManageOldProduct() throws IOException, SQLException, MongoException {
        String message = "Atención: existe en algún ticket un "
                + "producto con idénticas características (actualmente descatalogado en stocks).";
        io.writeln(message);
        YesNoDialog require = new YesNoDialog("Se trata del mismo producto o son distintos");
        controlador.setIsNew(!require.read());
    }

    private void saveProduct() throws IOException, SQLException, MongoException {
        int minInitUnits = controlador.getIncrementRange()[0];
        int maxInitUnits = controlador.getIncrementRange()[1];
        String message = "la cantidad inicial en stock";
        int cantidadInicial = new ProductoUnidadV()
                .askIncrement(message, minInitUnits, maxInitUnits);
        controlador.updateUnitsStock(cantidadInicial);
        controlador.updateTotalsValues();
        io.writeln("Producto añadido al stock con éxito");
    }
}
