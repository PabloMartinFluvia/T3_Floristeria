
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
    
    private final String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
    
    private final String atributo_tiquets = Floristeria.class.getDeclaredFields()[5].getName();
    
    private final String atributo_valorProductos = ConjuntoProductos.class.getDeclaredFields()[1].getName();                
    
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
    public ConjuntoProductos getOneStockById(int idStock) throws IOException {  
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);
        gestor.replaceNode_isArray_nodeByIndex(idStock-1);        
        @SuppressWarnings("unchecked")
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
    public List<ConjuntoProductos> getAllTiquets() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);
        @SuppressWarnings("unchecked")
        ConjuntoProductos[] tiquets = (ConjuntoProductos[]) gestor.parseNodeToObject(ConjuntoProductos[].class);
        return Arrays.asList(tiquets);
    }
    
}
