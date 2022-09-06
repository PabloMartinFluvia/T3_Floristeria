package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{
   
    /**
     * Guardar el tiquet (id, valor total) y establecer la relación con los productos
     * (incluyendo las cantidades de estos en el ticket)
     * @param tiquet
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void createTiquet(ConjuntoProductos tiquet) throws IOException, SQLException, MongoException;
    
    /**
     * Obtener el stock que tenga el id especificado.
     * @param idStock
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public ConjuntoProductos getOneStockById (int idStock)throws IOException, SQLException, MongoException;
    
    /**
     * Obtener los stocks de la floristería.
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public List<ConjuntoProductos> getAllStocks ()throws IOException, SQLException, MongoException; 
    
    /**
     * Obtener todos los tiquets que se han vendido. 
     * Si no hay tiquets almacenados devuelve una lista vacía.
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public List<ConjuntoProductos> getAllTiquets ()throws IOException, SQLException, MongoException; 
    
    /**
     * Incrementar el valor del total del stock indicado según el parametro proporcionado
     * @param idStock
     * @param incrementoValor
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */  
    public void incrementarValorEnStockById(int idStock, float incrementoValor) throws IOException, SQLException, MongoException; 
}
