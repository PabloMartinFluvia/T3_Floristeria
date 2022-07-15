package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;
import java.util.List;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    /*
    esta clase es para hacer las operaciones CRUD de un producto cualquiera,
    sin conocer / afectar a las unidades que pueda haber de este en algun conjunto
    */
    
    // create: añadir nuevo producto (luego hará que asociarlo a un stock con las unidades)
    // **** de momento ya lo hace el createProducto de Producto unidad
    
    
    public ProductoCompleto findProductoEnAllStocksById(int producto_id) throws IOException;
    
    /*
    a)
    b) parse stock_id to categoria + select in producto los de la esa categoria
    c) parse stock_id to categoria + find in productos los de esa categoria
    */
     public List<ProductoCompleto> getProductosSinUnidadesByStockId (int stock_id) throws IOException;//ok
    
    // -> NO: findAllByTiquetId -> no tiene sentido, lo relevante en un tiquet és el ProductoUnidad
    
     
    //update -> nunca, no se deja canviar el precio ni características
    
     
    //delete -> se encarga el prducto unidad
    
}
