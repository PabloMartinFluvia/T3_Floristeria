package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{
    
    @Override
    public void create(Floristeria entity) throws IOException{
        assert entity != null;
        entity.setFloristeria_id(1);
        gestor.writeInFile(entity); // nulls fields ignored + no appendable
    }
    
    @Override
    public String findName() throws IOException {        
        String nombreAtributo = Floristeria.class.getDeclaredFields()[1].getName();
        return gestor.getValueToString_fromFile(nombreAtributo);
    }

    @Override
    public void addToStockValue(float increment) throws IOException {
        // augmentar / reducir el valor del stock segun esta cantidad
    }
    
}