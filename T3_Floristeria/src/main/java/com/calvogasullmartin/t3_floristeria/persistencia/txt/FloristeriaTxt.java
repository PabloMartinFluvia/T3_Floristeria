package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    
    @Override
    public void create(Floristeria floristeria) throws IOException {
        assert floristeria != null;
        floristeria.setFloristeria_id(1); //se assigna el id aqui
        gestor.writeObjectInFile(floristeria);
    }
        
    @Override
    public String getName() throws IOException { 
        gestor.setMainNode_FromFile();
        String atributo_nombre_floristeria = Floristeria.class.getDeclaredFields()[1].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_nombre_floristeria);
        return gestor.getStringValue_fromNode();
    }

    @Override
    public float getValorFloristeria() throws IOException {            
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_valorFloristeria = Floristeria.class.getDeclaredFields()[2].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_valorFloristeria);
        System.out.println(gestor.test());
        return gestor.getFloatValue_fromNode();
    }

    @Override
    public float getFacturacionFloristeria() throws IOException {
        String nombreAtributoFacturacion = Floristeria.class.getDeclaredFields()[3].getName();
        gestor.setMainNode_FromFile();
        return gestor.getValueParsedToFloatOfFirstChildNode_x(nombreAtributoFacturacion);
    }
    
    @Override
    public void incrementarValorFloristeria(float incremento) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        String atributo_valorStocksTotal = Floristeria.class.getDeclaredFields()[2].getName(); 
        gestor.updateNode_incrementFloatValueInField(atributo_valorStocksTotal, incremento);
        gestor.saveMainNodeInFile();
    }   

    @Override
    public void incrementarFacturacionTotal(float incremento) throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[3].getName(); 
        gestor.setMainNode_FromFile();
        gestor.incrementMainFloatFieldInFile(nombreAtributoValor, incremento, Floristeria.class);
        gestor.saveMainNodeInFile();
    }
   
    
}
