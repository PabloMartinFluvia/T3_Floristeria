package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ShowStocksC;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalShowStocksC extends LocalShowConjuntosC implements ShowStocksC{

    private int indexStockTarget;
    
    private boolean isAllOptionsPossible;
    
    public LocalShowStocksC(Manager manager) {
        super(manager);
        isAllOptionsPossible = false;
    }

    @Override
    public void setIsWithUnits(boolean isWithUnits) {
        this.isWithUnits = isWithUnits;
    } 
    
    @Override
    public void setIsAllOptionsPossible(boolean isAllOptionsPossible) {
        this.isAllOptionsPossible = isAllOptionsPossible;
    }
                
    @Override
    public boolean isAllOptionsPossible() {
        return isAllOptionsPossible;
    }
    
    @Override
    public void setIndexStockTarget(int indexStockTarget) {
        assert indexStockTarget >= -1 && indexStockTarget < this.getNUM_CATEGORIAS();
        this.indexStockTarget = indexStockTarget;
    }       
    
    @Override
    public void readConjuntos() throws IOException, SQLException, MongoException {   
        errorBD = "Error! No se ha poddido leer los productos en stock.";
        if(indexStockTarget < 0){
            conjuntosLista = factory.getConjuntoProductosDao().getAllStocks();
        }else{            
            conjuntosLista.add(factory.getConjuntoProductosDao().getOneStockById(indexStockTarget + 1));
        }        
    }
    
    @Override
    public String getTitle() {        
        return "STOCK de "+Categoria.values()[conjuntoToShow.getConjunto_id()-1];
    }
    
    @Override
    public String getMensageCantidad() {
        return "actual en stock";
    }
    
    @Override
    public int getIndexStockTarget() {
        return indexStockTarget;
    }
    
    @Override
    public void setIsWithID(boolean isWithId) {
        this.isWithId = isWithId;
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        assert controlador != null;
        controlador.visit(this);
    }
}
