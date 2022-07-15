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
        gestor.getMainNodeFromFile();
        String atributo_nombre_floristeria = Floristeria.class.getDeclaredFields()[1].getName();
        gestor.setAuxiliarNode_findFieldByName(atributo_nombre_floristeria);
        return gestor.getAuxiliarNode_asText();
    }

    @Override
    public float getValorFloristeria() throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[2].getName();    
        gestor.getMainNodeFromFile();
        return gestor.getValueParsedToFloatOfFirstChildNode_x(nombreAtributoValor);
    }

    @Override
    public float getFacturacionFloristeria() throws IOException {
        String nombreAtributoFacturacion = Floristeria.class.getDeclaredFields()[3].getName();
        gestor.getMainNodeFromFile();
        return gestor.getValueParsedToFloatOfFirstChildNode_x(nombreAtributoFacturacion);
    }
    
    @Override
    public void incrementarValorFloristeria(float incremento) throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[2].getName();   
        gestor.getMainNodeFromFile();
        gestor.incrementMainFloatFieldInFile(nombreAtributoValor, incremento, Floristeria.class);
        gestor.saveMainNodeInFile();
    }   

    @Override
    public void incrementarFacturacionTotal(float incremento) throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[3].getName(); 
        gestor.getMainNodeFromFile();
        gestor.incrementMainFloatFieldInFile(nombreAtributoValor, incremento, Floristeria.class);
        gestor.saveMainNodeInFile();
    }
   
    
}
