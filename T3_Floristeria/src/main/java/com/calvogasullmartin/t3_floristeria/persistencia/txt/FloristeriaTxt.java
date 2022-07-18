package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    private final String atributo_nombre_floristeria = Floristeria.class.getDeclaredFields()[1].getName();
    
    @Override
    @SuppressWarnings("unchecked")
    public void create(Floristeria floristeria) throws IOException {
        assert floristeria != null;
        floristeria.setFloristeria_id(1); //se assigna el id aqui
        gestor.writeObjectInFile(floristeria);
    }
        
    @Override
    public String getName() throws IOException { 
        gestor.setMainNode_FromFile();        
        gestor.setNode_findFieldByName_fromMain(atributo_nombre_floristeria);
        return gestor.getStringValue_fromNode();
    }
}
