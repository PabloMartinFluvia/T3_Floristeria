package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.controladores.ElegirProductoC;
import com.calvogasullmartin.t3_floristeria.controladores.PersistenciaC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public abstract class ElegirProductoV {

    protected InOut io;

    protected EndV endV;

    public ElegirProductoV(PersistenciaC controlador) {
        assert controlador != null;
        io = new InOut();
        endV = new EndV(controlador);
    }

    protected void update(ElegirProductoC controlador, String operationOption) throws IOException, SQLException, MongoException {
        if (isProductChoosed(controlador, operationOption)) {
            updateProduct();
        }
    }

    private boolean isProductChoosed(ElegirProductoC controlador, String operationOption) throws IOException, SQLException, MongoException {
        String messageSelectStock = "el tipo de producto que desea " + operationOption;
        new ShowConjuntosV().printStock(controlador.getNewShowStocksC(), messageSelectStock);
        if (!controlador.isStockEmpty()) {
            return chooseProduct(controlador, operationOption);
        } else {
            io.writeln("Atención. No hay productos almazenados en este stock");
            return false;
        }
    }

    private boolean chooseProduct(ElegirProductoC controlador, String operationOption) throws IOException, SQLException, MongoException {
        String messageSelectId
                = "Introduzca el ID del producto que desea " + operationOption + ": ";
        String messageWrongSelection = "Id seleccionado incorrecto. Desea intentarlo de nuevo";
        YesNoDialog requirement = new YesNoDialog(messageWrongSelection);
        boolean chooseCancel = false;
        do {
            int productoId = io.readInt(messageSelectId);
            if (controlador.isIdValid(productoId)) {
                return true;
            } else {
                chooseCancel = !requirement.read();
            }
        } while (!chooseCancel);
        return false;
    }

    protected abstract void updateProduct() throws IOException, SQLException, MongoException;
}
