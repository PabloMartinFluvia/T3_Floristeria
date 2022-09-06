package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.GestionarUnidadesC;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public abstract class LocalGestionarUnidadesC extends LocalPersistenciaC implements GestionarUnidadesC{   
    
    protected ProductoUnidad productoUnidadUpdating;
    
    protected int stock_id_updating;
    
    protected float[] variacionValorStocks = new float[getNUM_CATEGORIAS()];        
    
    public LocalGestionarUnidadesC(Manager manager) {
        super(manager);
        resetProductUpdated();
    }
    
    @Override
    public int[] getIncrementRange() {
        int actualUnits = productoUnidadUpdating.getCantidad();
        int maxIncrement = getMAX_UNIDADES_EN_STOCK() - actualUnits;
        int minIncrement = -actualUnits;
        int[] range = {minIncrement, maxIncrement};
        return range;
    }

    @Override
    public abstract void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException;

    @Override
    public void updateTotalsValues() throws IOException, SQLException, MongoException {
        float variacionTotal = 0f;
        for (int i = 0; i < variacionValorStocks.length; i++){    
            errorBD = "Error! No se ha podido actuzlizar el valor del stock de "+Categoria.values()[i];
            factory.getConjuntoProductosDao().incrementarValorEnStockById(i+1, variacionValorStocks[i]);
            variacionTotal += variacionValorStocks[i];
        }
        errorBD = "Error! No se ha podido actualizar el valor total de la floristería";
        factory.getFloristeriaDao().incrementarValorFloristeria(variacionTotal);
    }
    
    @Override
    public final void resetProductUpdated(){
        productoUnidadUpdating = new ProductoUnidad();
        variacionValorStocks = new float[getNUM_CATEGORIAS()];
    }    
}
