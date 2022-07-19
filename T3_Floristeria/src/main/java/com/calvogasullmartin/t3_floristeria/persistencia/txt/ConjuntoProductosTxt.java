
package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import java.io.IOException;

public class ConjuntoProductosTxt extends GenericDaoTxt<ConjuntoProductos, Integer> implements ConjuntoProductosDao{
    
    private final String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
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
    
}
