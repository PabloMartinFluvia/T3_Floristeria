package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;
import java.util.List;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    
    /*
    a)
    b) parse stock_id to categoria + select in producto los de la esa categoria
    c) parse stock_id to categoria + find in productos los de esa categoria
    */
     public List<ProductoCompleto> getProductosSinUnidadesByStockId (int stock_id) throws IOException;
    
    public ProductoCompleto findById(int id);
    
}
