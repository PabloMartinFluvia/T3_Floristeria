package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalConjuntosC;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;

public class LocalStocksC extends LocalConjuntosC implements StocksC{
        
    public LocalStocksC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }
    
    @Override
    public void getStocks(int stockIndex) throws IOException {
        if(stockIndex < 0){
            conjuntos = factory.getConjuntoProductosDao().getAllStocks();
        }else{
            conjuntos.add(factory.getConjuntoProductosDao().getOneStockById(stockIndex + 1));
        }
    }

    @Override
    public String getTitle(int indexConjunto) {
        assert indexConjunto < conjuntos.size();
        int idConjunto = conjuntos.get(indexConjunto).getId();
        return "STOCK de "+Categoria.values()[idConjunto-1];
    }

    @Override
    public String getMensageCantidad() {
        return "actual en stock";
    }
}
