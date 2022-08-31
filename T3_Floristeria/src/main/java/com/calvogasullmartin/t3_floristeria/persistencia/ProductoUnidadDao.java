package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.sql.SQLException;

public interface ProductoUnidadDao extends GenericDao<ProductoUnidad, Integer>{
    
    
    
    /**
     * crear el producto + relacionar la cantidad con el stock
     * @param productoUnidad
     * @param stock_id
     * @throws IOException 
     * @throws java.sql.SQLException 
     */
    public void createNuevoProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException, SQLException; 

    /**
     * associar el id del producto (que estaba descatalogado pero almacenado debido a relación conticket) al stock
     * y assignar la cantidad
     * @param productoUnidad
     * @param stock_id
     * @throws IOException 
     * @throws java.sql.SQLException 
     */
    public void reCatalogarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException, SQLException; 
    
   /**
    * si no existe la relación entre el producto y el stock -> el dao devuelve un número
    * negativo (equivalente a un null).
    * @param stock_id
    * @param producto_id
    * @return
    * @throws IOException 
     * @throws java.sql.SQLException 
    */
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws IOException, SQLException; 
    
    /*    
    c) find in floristeria  stocks.id-1.products with one has reference to productoId 
    + set cantidad: {$incr:}
    */
    public void incrementarCantidadByStockIdProductoId (int stockId,int productoId,int incremento) throws IOException, SQLException;
    
    /*
    c) find in floristeria tickets match productoId =
        if found null -> false; else -> true
    */
    public boolean isSoldAnytimeById(int productoId) throws IOException, SQLException;
    
    /*
    c) find in floristeria collections conjunto.idConjunto the one with idProductos as reference and remove
    */
    public void eliminarRelacionConStock(int stock_id, int producto_id) throws IOException, SQLException;
}    
