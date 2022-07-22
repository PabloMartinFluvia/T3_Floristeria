package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto2;
import java.io.IOException;

public interface ProductoCompletoDao2 extends GenericDao2<ProductoCompleto2, Integer>{
    
    /*
    b) select id from productos (tabla) where categoria = categoria & altura = and color = and material= and name=   
    c) find idem en productos collection
    */
    
    //se "ignora" en que stock está
    public int getProductoId (ProductoCompleto2 producto) throws IOException;
    
    // read: obtener el producto  
    /*
    b) select producto columns from projuctos join conjunto_has_productos where id_p= && id_s=
    c) find form floristeria stocks.stock_id.productos where produto_id = + lookup with products
        and project only producto fields
    */
    public ProductoCompleto2 findProductoByIdInStockId(int producto_id, int stock_id) throws IOException;
}
