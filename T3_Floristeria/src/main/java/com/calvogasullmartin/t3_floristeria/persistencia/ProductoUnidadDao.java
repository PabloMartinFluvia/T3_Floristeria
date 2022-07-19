package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;

public interface ProductoUnidadDao extends GenericDao<ProductoUnidad, Integer>{
    
    
    
    /**
     * Si el producto unidad tiene alguna id asignada implica que el producto tiene relación con algun tiquet
     * (y el producto completo ya está almacenado en la BD) -> solo hay que establecer la relación con el stock
     * y assignarle la cantidad.
     * 
     * En caso contrario (!producto_id>0): hay que crear primero el producto (con un nuevo id)
     * y luego hacer el paso anterior.
     * @param productoUnidad
     * @param stock_id
     * @throws IOException 
     */
    public void createNuevoProductoUnidadEnStocks(ProductoUnidad productoUnidad, int stock_id) throws IOException;
    
   /**
    * si no existe la relación entre el producto y el stock -> el dao devuelve un número
    * negativo (equivalente a un null).
    * @param stock_id
    * @param producto_id
    * @return
    * @throws IOException 
    */
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws IOException;
}
