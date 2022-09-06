package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.CategoriaV;
import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.ConjuntoProductosV;
import com.calvogasullmartin.t3_floristeria.controladores.ShowConjuntosC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowTicketsC;
import com.calvogasullmartin.t3_floristeria.controladores.ShowStocksC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class ShowConjuntosV {
    
    private final InOut io;   
    
    private EndV endV;
    
    public ShowConjuntosV(){
        io = new InOut();                
    }
    
    public void interact(ShowTicketsC controlador){      
        assert controlador != null;
        endV = new EndV(controlador);
        try {
            mostrarListado(controlador);   
            controlador.resetConjuntosReaded();
            new EndV(controlador).waitBeforeToMenu();
        } catch (IOException | SQLException | MongoException ex) {            
            new EndV(controlador).manageError(ex);
        }                
    }
    
    public void interact(ShowStocksC controlador){ 
        assert controlador != null;
        boolean isWithUnits = new YesNoDialog("Desea visualizar las unidades").read();
        controlador.setIsWithUnits(isWithUnits);     
        controlador.setIsAllOptionsPossible(true);
        endV = new EndV(controlador);
        try {        
            printStock(controlador, "que stock(s) desea ver");
            controlador.resetConjuntosReaded();
            endV.askRepeatAction("volver a consultar algun otro stock");
        } catch (IOException | SQLException | MongoException ex) {            
            endV.manageError(ex);
        }                
    }
    
    //método de esta vista que se utilizará en todas las funcionalidades que 
    //impliquen seleccionar un producto (para poder manipular las unidades en stock)
    public void printStock(ShowStocksC controlador, String mensageOpcion)throws IOException, SQLException, MongoException{
        assert controlador != null;
        assert mensageOpcion != null;
        boolean allOptionsPosible = controlador.isAllOptionsPossible();        
        int categoriaIndex = new CategoriaV().askForCategoriaIndex(allOptionsPosible, mensageOpcion);        
        controlador.setIndexStockTarget(categoriaIndex);   
        mostrarListado(controlador);
    }    

    private void mostrarListado (ShowConjuntosC controlador)throws IOException, SQLException, MongoException{
        controlador.readConjuntos();
        new ConjuntoProductosV().mostrarConjuntos(controlador);        
    }       
}
