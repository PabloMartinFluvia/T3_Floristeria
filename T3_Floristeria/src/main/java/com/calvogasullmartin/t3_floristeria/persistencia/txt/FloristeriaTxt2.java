package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao2;

public class FloristeriaTxt2 extends GenericDaoTxt2<Floristeria2, Integer> implements FloristeriaDao2{

    private final String atributo_nombre = Floristeria2.class.getDeclaredFields()[1].getName();
    private final String atributo_valor = Floristeria2.class.getDeclaredFields()[2].getName();
    private final String atributo_facturacion = Floristeria2.class.getDeclaredFields()[3].getName();
    
    @Override
    @SuppressWarnings("unchecked")
    public void create(Floristeria2 floristeria) throws IOException {
        assert floristeria != null;
        floristeria.setFloristeria_id(1); //se assigna el id aqui
        gestor.writeObjectInFile(floristeria);
    }
        
    @Override
    public String getName() throws IOException { 
        gestor.setMainNode_FromFile();        
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
    public void incrementarValorFloristeria(float incremento) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        gestor.updateNode_incrementFloatValueInField(atributo_valor, incremento);
        gestor.saveMainNodeInFile();
    }

    @Override
    public void incrementarFacturacionFloristeria(float incremento) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        gestor.updateNode_incrementFloatValueInField(atributo_facturacion, incremento);
        gestor.saveMainNodeInFile();
    }
}
