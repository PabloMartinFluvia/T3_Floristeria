package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    
    @Override
    public void create(Floristeria floristeria) throws IOException {
        assert floristeria != null;
        floristeria.setFloristeria_id(1); //se l'assigna aquí
        gestor.writeObjectInFile(floristeria);// nulls fields ignored + no appendable        
    }
        
    @Override
    public String getName() throws IOException { //no hay findById, ya que solo hay una floristeria
        String nombreAtributoNombre = Floristeria.class.getDeclaredFields()[1].getName();         
        return gestor.getValueParsedToStringOfFirstFieldMatchesName_fromFile(nombreAtributoNombre);
    }

    @Override
    public float getValorFloristeria() throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[2].getName();        
        return gestor.getValueParsedToFloatOfFirstFieldMatchesName_fromFile(nombreAtributoValor);
    }

    @Override
    public float getFacturacionFloristeria() throws IOException {
        String nombreAtributoFacturacion = Floristeria.class.getDeclaredFields()[3].getName();
        return gestor.getValueParsedToFloatOfFirstFieldMatchesName_fromFile(nombreAtributoFacturacion);
    }
    
    @Override
    public void incrementarValorFloristeria(float incremento) throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[2].getName();        
        gestor.incrementMainFloatFieldInFile(nombreAtributoValor, incremento, Floristeria.class);
    }   

    @Override
    public void incrementarFacturacionTotal(float incremento) throws IOException {
        String nombreAtributoValor = Floristeria.class.getDeclaredFields()[3].getName();        
        gestor.incrementMainFloatFieldInFile(nombreAtributoValor, incremento, Floristeria.class);
    }
   
    
}
