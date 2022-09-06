package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface FloristeriaDao extends GenericDao<Floristeria, Integer>{
        
    /**
     * Almacena en la BD los datos iniciales de la floristería
     *      Se ejecuta solo la primera vez que corre la App o cuando la BD no ha sido 
     *      correctamente inicializada
     * @param floristeria
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void create(Floristeria floristeria)throws IOException, SQLException, MongoException; 
        
    /**
     * Obtener el nombre de la floristería.
     * Puede devolver null -> solo si la BD está inicializada de manera incorrecta
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public String getName() throws IOException, SQLException, MongoException; 
        
    /**
     * Obtener el valor de los productos de la floristería (equivalente a sumatorio 
     * de precio*cantidad de todos los productos en los stocks)     
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public float getValorFloristeria() throws IOException, SQLException, MongoException; 
    
    /**
     * Obtener el total facturado de la floristería (equivalente a sumatorio 
     * de precio*cantidad de todos los productos en los tickets)     
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public float getFacturacionFloristeria() throws IOException, SQLException, MongoException; 
       
    /**
     * Incrementar el valor del total de los stocks según el parametro proporcionado
     * @param incrementoValor
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void incrementarValorFloristeria(float incrementoValor) throws IOException, SQLException, MongoException; 
    
    /**
     * Incrementar el valor del total facturado segín el parametro proporcionado
     * @param incrementoValor
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void incrementarFacturacionFloristeria(float incrementoValor) throws IOException, SQLException, MongoException; 
    
}
