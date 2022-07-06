package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaDaoTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    @Override
    public String findName() throws IOException {        
        String nombreAtributo = Floristeria.class.getDeclaredFields()[1].getName();
        return gestor.getValueToString_fromFile(nombreAtributo);
    }
    
}
