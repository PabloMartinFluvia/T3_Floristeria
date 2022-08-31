
package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductosTxt extends GenericDaoTxt<ConjuntoProductos, Integer> implements ConjuntoProductosDao{
    
    private final String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
    
    private final String atributo_tiquets = Floristeria.class.getDeclaredFields()[5].getName();
    
    private final String atributo_stockId = ConjuntoProductos.class.getDeclaredFields()[0].getName();                
    
    private final String atributo_valorProductos = ConjuntoProductos.class.getDeclaredFields()[1].getName();                
    
     @Override
    @SuppressWarnings("unchecked")
    public void createTiquet(ConjuntoProductos tiquet) throws IOException {
        int maxActualId = findMaxTiquetId();    
        tiquet.setConjunto_id(maxActualId + 1);
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets); 
        gestor.setAuxiliarNode_ObjectInput(tiquet);
        gestor.updateNode_isArray_pushAuxiliarNode(); 
        gestor.saveMainNodeInFile();
    }
    
    private int findMaxTiquetId() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);      
        gestor.setListNodes_findAllFieldsByName(atributo_stockId);        
        return gestor.getMaxIntValue_fromListNodes();
    }
    
    @Override
    public void incrementarValorEnStockById(int idStock, float incrementoValor) throws IOException {
        gestor.setMainNode_FromFile();        
        gestor.setAuxiliarNodesNull();        
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);               
        gestor.replaceNode_isArray_nodeByIndex(idStock-1);   
        gestor.updateNode_incrementFloatValueInField(atributo_valorProductos, incrementoValor);
        gestor.saveMainNodeInFile();  
    }

    @Override
    @SuppressWarnings("unchecked")
    public ConjuntoProductos getOneStockById(int idStock) throws IOException {  
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);
        gestor.replaceNode_isArray_nodeByIndex(idStock-1);                
        ConjuntoProductos stock = (ConjuntoProductos) gestor.parseNodeToObject(ConjuntoProductos.class);                
        return  stock;
    }

    @Override
    public List<ConjuntoProductos> getAllStocks() throws IOException {
        List<ConjuntoProductos> stocks = new LinkedList<>();
        int num_stocks = Categoria.values().length;
        for (int i = 0; i<num_stocks; i++){            
            stocks.add(getOneStockById(i+1));
        }
        return stocks;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ConjuntoProductos> getAllTiquets() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);        
        ConjuntoProductos[] tiquets = (ConjuntoProductos[]) gestor.parseNodeToObject(ConjuntoProductos[].class);
        return Arrays.asList(tiquets);
    }
}
