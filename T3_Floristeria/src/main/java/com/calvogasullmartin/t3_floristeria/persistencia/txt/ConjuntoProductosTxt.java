package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductosTxt extends GenericDaoTxt<ConjuntoProductos, Integer> implements ConjuntoProductosDao{

    @Override
    public void createNewTiquet(ConjuntoProductos tiquet) throws IOException {
        int max_id = findMaxProductId();
        tiquet.setId(max_id + 1);
        String arrayFieldName = Floristeria.class.getDeclaredFields()[5].getName();
        gestor.setMainNode_FromFile();
        gestor.addObjectInUniqueArray(tiquet, arrayFieldName);
        gestor.saveMainNodeInFile();
    }
    
    private int findMaxProductId() throws IOException{
        String nombreAtributoId = ConjuntoProductos.class.getDeclaredFields()[0].getName();
        gestor.setMainNode_FromFile();
        return gestor.findMaxIntValueInMultipleChildNodes(nombreAtributoId);
    }
    
    @Override
    public void incrementarValorEnStockById(int stock_id, float increment) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName(); 
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks        
        gestor.replaceNode_isArray_nodeByIndex(stock_id-1); //stock
        String atributo_valorProductos = ConjuntoProductos.class.getDeclaredFields()[1].getName();        
        gestor.updateNode_incrementFloatValueInField(atributo_valorProductos, increment);//stock field updated
        gestor.saveMainNodeInFile();        
    }

    @Override
    public ConjuntoProductos getOneStockById(int id) throws IOException {
        String stocksFieldName = Floristeria.class.getDeclaredFields()[4].getName();   
        gestor.setMainNode_FromFile();
        ConjuntoProductos stock = (ConjuntoProductos) gestor.getObjectIndexedInArray(stocksFieldName,ConjuntoProductos.class, id-1);
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
    public List<ConjuntoProductos> getAllTiquets() throws IOException {
        String tiquetsFieldName = Floristeria.class.getDeclaredFields()[5].getName();  
        gestor.setMainNode_FromFile();
        ConjuntoProductos[] arrayTiquets =  (ConjuntoProductos[]) gestor.getObjectArray(tiquetsFieldName,ConjuntoProductos[].class);        
        List<ConjuntoProductos> tiquets = Arrays.asList(arrayTiquets);        
        return tiquets;
    }

    

    
    
}
