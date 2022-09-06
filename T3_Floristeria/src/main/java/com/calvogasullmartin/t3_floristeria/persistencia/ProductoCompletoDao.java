package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    /**
     * Se busca si ya existe un producto con idénticas características almacenado en la BD
     *      Se ignora el id que pueda tener assignado el producto proporcionaco como argumento
     *      La busqueda se realiza tanto si está associado a un stock / ticket / no relación
     * Si lo encuentra devuelve el id.
     * Si no lo encuentra devuelve id = 0;
     * @param producto
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */    
    public int findId(ProductoCompleto producto) throws IOException, SQLException, MongoException; 
    
    /**
     * Comprueva que el existe un producto con ese id en el stock indicado.
     * Si existe devuelve el producto.
     * Si no existe devuelve null
     * 
     * @param producto_id
     * @param stock_id
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws IOException, SQLException, MongoException; 
    
    
    /**
     * Elimina cualquier dato del producto almacenado en la BD
     *  solo se invoca cuando el producto no tiene relación con ningún ticket (nunca vendido)   
     * 
     * @param producto
     * @throws IOException
     * @throws SQLException
     * @throws MongoException 
     */
    public void eliminarProducto(ProductoCompleto producto) throws IOException, SQLException, MongoException;
}
