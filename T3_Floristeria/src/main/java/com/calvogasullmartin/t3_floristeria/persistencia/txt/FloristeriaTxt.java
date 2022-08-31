package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    private final String atributo_nombre = Floristeria.class.getDeclaredFields()[1].getName();
    
    private final String atributo_valor = Floristeria.class.getDeclaredFields()[2].getName();
    
    private final String atributo_facturacion = Floristeria.class.getDeclaredFields()[3].getName();
    
    @Override
    @SuppressWarnings("unchecked")
    public void create(Floristeria floristeria) throws IOException {
        floristeria.setFloristeria_id(1); 
        gestor.writeObjectInFile(floristeria);
    }
        
    @Override
    public String getName() throws IOException { 
        gestor.setMainNode_FromFile();     
        gestor.setAuxiliarNodesNull();      
        gestor.setNode_findFieldByName_fromMain(atributo_nombre);
        return gestor.getStringValue_fromNode();
    }

    @Override
    public float getValorFloristeria() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();        
        gestor.setNode_findFieldByName_fromMain(atributo_valor);        
        return gestor.getFloatValue_fromNode();
    }

    @Override
    public float getFacturacionFloristeria() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();        
        gestor.setNode_findFieldByName_fromMain(atributo_facturacion);        
        return gestor.getFloatValue_fromNode();
    }

    @Override
    public void incrementarValorFloristeria(float incrementoValor) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        gestor.updateNode_incrementFloatValueInField(atributo_valor, incrementoValor);
        gestor.saveMainNodeInFile();
    }

    @Override
    public void incrementarFacturacionFloristeria(float incrementoValor) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        gestor.updateNode_incrementFloatValueInField(atributo_facturacion, incrementoValor);
        gestor.saveMainNodeInFile();
    }
}
