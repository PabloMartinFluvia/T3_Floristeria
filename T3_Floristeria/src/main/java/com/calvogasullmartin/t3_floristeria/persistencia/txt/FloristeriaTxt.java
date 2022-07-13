package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public class FloristeriaTxt extends GenericDaoTxt<Floristeria, Integer> implements FloristeriaDao{

    /*
    Equivale a create / insert
    a) El documento floristeria, incluyendo la estructura de los documentos embebed
    b) El registro de la tabla Floristeria (insert into) + la creación de todas las tablas de la BD
    c) ídem a (a) (insertOne)
    */
    @Override
    public void create(Floristeria floristeria) throws IOException {
        assert floristeria != null;
        floristeria.setFloristeria_id(1); //se l'assigna aquí
        gestor.writeObjectInFile(floristeria);// nulls fields ignored + no appendable        
    }
    
    /*
    Equivale a read
    a) el valor del campo nombre, en el documento de la floristeria
    b) select name from Floristeria where floristeria_id = 1
    c) find floristeria + project nombre (y luego coger el valor del bson document)    
    si no encutra el campo o el valor no es un string devuelve null
    */
    @Override
    public String getName() throws IOException { //no hay findById, ya que solo hay una floristeria
        String nombreAtributoNombre = Floristeria.class.getDeclaredFields()[1].getName();         
        return gestor.getValueParsedToStringOfFirstFieldMatchesName_fromFile(nombreAtributoNombre);
    }

    @Override
    public void addToStockValue(float increment) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float findTotalStocks() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float findTotalTiquets() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
