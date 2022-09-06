package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ProductoUnidadDao extends GenericDao<ProductoUnidad, Integer>{
        
     /**
      * crear el producto + relacionar-lo con el stock (indicando la cantidad)
      * @param productoUnidad
      * @param stock_id
      * @throws IOException
      * @throws SQLException
      * @throws MongoException 
      */
    public void createNuevoProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException, SQLException, MongoException; 
    
     /**
      * Associar el producto con el stock (indicando la cantidad)
      *      La info del producto ya está almacenada en la BD
      * Solo se invoca a este método cuando el producto estaba descatalogado pero
      * la info / detalles de este seguían en la BD debido a que está asociado a algun ticket.
      * @param productoUnidad
      * @param stock_id
      * @throws IOException
      * @throws SQLException
      * @throws MongoException 
      */
    public void reCatalogarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException, SQLException, MongoException; 
    
    /**
     * Obtener la cantidad de este producto en ese stock.
     * Si no existe la relación entre el producto y el stock -> el dao devuelve un número
     * negativo (equivalente a un null).
     * @param stock_id
     * @param producto_id
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws IOException, SQLException, MongoException; 
    
    /**
     * Incrementar la cantidad del producto (que está associado a ese stock)
     * @param stockId
     * @param productoId
     * @param incremento
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void incrementarCantidadByStockIdProductoId (int stockId,int productoId,int incremento) throws IOException, SQLException, MongoException;
    
    /**
     * Comprueva si el producto con ese id está associado a algun ticket.
     * 
     * @param productoId
     * @return
     * @throws IOException
     * @throws SQLException 
     */
    public boolean isSoldAnytimeById(int productoId) throws IOException, SQLException, MongoException;
    
    /**
     * Elimina la relación de el producto (y la cantidad de este) con el stock indicado.
     * @param stock_id
     * @param producto_id
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void eliminarRelacionConStock(int stock_id, int producto_id) throws IOException, SQLException, MongoException;
}    
