package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalConjuntosC2;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.StocksC2;

public class LocalStocksC2 extends LocalConjuntosC2 implements StocksC2{
        
    public LocalStocksC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC2 controlador) {
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
        return "STOCK de "+Categoria2.values()[idConjunto-1];
    }

    @Override
    public String getMensageCantidad() {
        return "actual en stock";
    }
}
