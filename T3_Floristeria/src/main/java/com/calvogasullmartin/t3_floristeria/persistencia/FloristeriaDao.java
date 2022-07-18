package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public interface FloristeriaDao extends GenericDao<Floristeria, Integer>{
    
    /*
    Equivale a create / insert
    a) El documento floristeria, incluyendo la estructura de los documentos embebed
    b) El registro de la tabla Floristeria (insert into) + la creación de todas las tablas de la BD
    c) ídem a (a) (insertOne)
    */
    public void create(Floristeria floristeria)throws IOException; //ok
    
    
    //READ
    
    //no hay findById, ya que solo hay una floristeria
    
    /*
    a) el valor del campo nombre, en el documento de la floristeria
    b) select name from Floristeria where floristeria_id = 1
    c) find floristeria + project nombre (y luego coger el valor del bson document)    
    si no encutra el campo o el valor no es un string devuelve null
    */
    public String getName() throws IOException; //ok
    
    
}
