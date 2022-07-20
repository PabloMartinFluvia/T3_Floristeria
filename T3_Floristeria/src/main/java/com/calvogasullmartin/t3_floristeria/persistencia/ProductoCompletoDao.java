package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    /*
    b) select id from productos (tabla) where categoria = categoria & altura = and color = and material= and name=   
    c) find idem en productos collection
    */
    
    //se "ignora" en que stock está
    public int getProductoId (ProductoCompleto producto) throws IOException;
}
