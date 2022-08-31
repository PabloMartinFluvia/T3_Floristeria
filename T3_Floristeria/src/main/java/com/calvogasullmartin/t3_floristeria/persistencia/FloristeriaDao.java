package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;
import java.sql.SQLException;

public interface FloristeriaDao extends GenericDao<Floristeria, Integer>{
    
    /*   
    c) documento inicial en la colección floristeria
    */
    public void create(Floristeria floristeria)throws IOException, SQLException; 
    
    /*
    c) find floristeria + project nombre (y luego coger el valor del bson document)    
    si no encutra el campo o el valor no es un string devuelve null
    */
    public String getName() throws IOException, SQLException; 
    
    /*    
    c) find floristeria + project valor del stock (y luego coger el valor del bson document)    
    si no encutra el campo o el valor no es un string devuelve null
    */
    public float getValorFloristeria() throws IOException, SQLException; 
    
    /*    
    c) find floristeria + project valor del total facturacion (y luego coger el valor del bson document)    
    si no encutra el campo o el valor no es un string devuelve null
    */
    public float getFacturacionFloristeria() throws IOException, SQLException; 
    
    /*
    c)  db.restaurant.updateOne({ "floristeria_id" : 1 },{ $set: { "valorTotal" : : {$incr: increment} }
    */
    public void incrementarValorFloristeria(float incrementoValor) throws IOException, SQLException; 
    
    /*
    c)  db.restaurant.updateOne({ "floristeria_id" : 1 },{ $set: { "    b) update Floristeria set valorFactutacion = valorTotal+increment where floristeria_id = 1;
" : : {$incr: increment} }
    */
    public void incrementarFacturacionFloristeria(float incrementoValor) throws IOException, SQLException; 
    
}
