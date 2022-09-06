package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ElegirProductoC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowStocksC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public abstract class LocalElegirProductoC extends LocalGestionarUnidadesC implements ElegirProductoC{        
    
    protected LocalShowStocksC controladorShowStocks;        
    
    public LocalElegirProductoC(Manager manager) {
        super(manager);
    }
    
    @Override
    public ShowStocksC getNewShowStocksC() {
        controladorShowStocks = new LocalShowStocksC(manager);
        controladorShowStocks.setIsWithID(true);
        return controladorShowStocks;
    }
    
    @Override
    public boolean isStockEmpty(){
        boolean isEmpty = controladorShowStocks.getNumProductosToShow() == 0;
        controladorShowStocks.resetConjuntosReaded();
        return isEmpty;
    }

    @Override
    public boolean isIdValid(int productoId) throws IOException, SQLException, MongoException {
        stock_id_updating = controladorShowStocks.getIndexStockTarget() + 1;
        errorBD = "Error! No se ha podido buscar el producto en la BD.";
        ProductoCompleto producto = DaoFactory.getFactory().getProductoCompletoDao().findProductoByIdInStockId(productoId, stock_id_updating);
        errorBD = "Error! No se ha podido buscar la cantidad en stock en la BD.";
        int cantidad = DaoFactory.getFactory().getProductoUnidadDao().getCantidadEnStockBy(stock_id_updating, productoId);                
        if (producto == null){               
            return false;//no encontrado
        }else if (cantidad <0) {
            errorBD = "Error! Cantidad de producto en stock <0.";
            throw new IOException();
        }else{
            productoUnidadUpdating.setProducto(producto);
            productoUnidadUpdating.setCantidad(cantidad);
            return true;
        }
    }

    @Override
    public abstract void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException;
}
