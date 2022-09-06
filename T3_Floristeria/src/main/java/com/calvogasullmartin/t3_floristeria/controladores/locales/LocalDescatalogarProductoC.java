package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.DescatalogarProductoC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalDescatalogarProductoC extends LocalElegirProductoC implements DescatalogarProductoC{

    public LocalDescatalogarProductoC(Manager manager) {
        super(manager);
    }

    @Override
    /**
     * Si nunca vendido se elimina completamente de la BBDD. Si alguna vez vendido
     * se elimina la relación con el stock, però permanece en la BBDD para que no se pierda la info
     * del producto.
     */
    public void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException{
        int productoId = getProductoId();
        errorBD = "Error! No se ha podido comprovar si se ha vendido alguna vez.";
        if (DaoFactory.getFactory().getProductoUnidadDao().isSoldAnytimeById(productoId)){            
            errorBD = "Error! No se ha podido eliminar la relación del producto con el stock";
            DaoFactory.getFactory().getProductoUnidadDao().eliminarRelacionConStock(stock_id_updating, productoId);
        }else {//nunca vendido
            errorBD = "Error! No se ha podido eliminar el producto de la BD.";
            DaoFactory.getFactory().getProductoCompletoDao().eliminarProducto(productoUnidadUpdating.getProducto());
        }
        float canvioValor = incremento * productoUnidadUpdating.getProducto().getPrecio();
        variacionValorStocks[stock_id_updating-1] += canvioValor;
    }

    @Override
    public int getProductoId() {
        return productoUnidadUpdating.getProducto().getProducto_id();
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        controlador.visit(this);
    }    
}
