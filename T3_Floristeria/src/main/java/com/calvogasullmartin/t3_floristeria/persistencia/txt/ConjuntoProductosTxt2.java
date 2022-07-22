
package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria2;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos2;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria2;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao2;

public class ConjuntoProductosTxt2 extends GenericDaoTxt2<ConjuntoProductos2, Integer> implements ConjuntoProductosDao2{
    
    private final String atributo_stocks = Floristeria2.class.getDeclaredFields()[4].getName();
    
    private final String atributo_tiquets = Floristeria2.class.getDeclaredFields()[5].getName();
    
    private final String atributo_stockId = ConjuntoProductos2.class.getDeclaredFields()[0].getName();                
    
    private final String atributo_valorProductos = ConjuntoProductos2.class.getDeclaredFields()[1].getName();                
    
     @Override
    @SuppressWarnings("unchecked")
    public void createTiquet(ConjuntoProductos2 tiquet) throws IOException {
        int maxActualId = findMaxTiquetId();    
        tiquet.setId(maxActualId + 1);
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets); //tiquets 
        gestor.setAuxiliarNode_ObjectInput(tiquet);
        gestor.updateNode_isArray_pushAuxiliarNode(); 
        gestor.saveMainNodeInFile();
    }
    
    private int findMaxTiquetId() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);//stocks        
        gestor.setListNodes_findAllFieldsByName(atributo_stockId); // all tiquetsId in stockS        
        return gestor.getMaxIntValue_fromListNodes();
    }
    
    @Override
    public void incrementarValorEnStockById(int idStock, float incremento) throws IOException {
        gestor.setMainNode_FromFile();        
        gestor.setAuxiliarNodesNull();        
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks                
        gestor.replaceNode_isArray_nodeByIndex(idStock-1); //stock  
        gestor.updateNode_incrementFloatValueInField(atributo_valorProductos, incremento);//stock field updated
        gestor.saveMainNodeInFile();  
    }

    @Override
    public ConjuntoProductos2 getOneStockById(int idStock) throws IOException {  
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);
        gestor.replaceNode_isArray_nodeByIndex(idStock-1);        
        @SuppressWarnings("unchecked")
        ConjuntoProductos2 stock = (ConjuntoProductos2) gestor.parseNodeToObject(ConjuntoProductos2.class);                
        return  stock;
    }

    @Override
    public List<ConjuntoProductos2> getAllStocks() throws IOException {
        List<ConjuntoProductos2> stocks = new LinkedList<>();
        int num_stocks = Categoria2.values().length;
        for (int i = 0; i<num_stocks; i++){            
            stocks.add(getOneStockById(i+1));
        }
        return stocks;
    }

    @Override
    public List<ConjuntoProductos2> getAllTiquets() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);
        @SuppressWarnings("unchecked")
        ConjuntoProductos2[] tiquets = (ConjuntoProductos2[]) gestor.parseNodeToObject(ConjuntoProductos2[].class);
        return Arrays.asList(tiquets);
    }

   
    
}
