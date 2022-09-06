package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ModificarUnidadesC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalModificarUnidadesC extends LocalElegirProductoC implements ModificarUnidadesC{

    public LocalModificarUnidadesC(Manager manager) {
        super(manager);
    }
    
    @Override
    public void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException {
        int productoId = productoUnidadUpdating.getProducto().getProducto_id();        
        errorBD = "Error! No se ha podido actualizar la cantidad en la BD.";
        DaoFactory.getFactory().getProductoUnidadDao()
                .incrementarCantidadByStockIdProductoId(stock_id_updating, productoId, incremento);
        float canvioValor = incremento * productoUnidadUpdating.getProducto().getPrecio();
        variacionValorStocks[stock_id_updating-1] += canvioValor;
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        controlador.visit(this);
    }    
}
