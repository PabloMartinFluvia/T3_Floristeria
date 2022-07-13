package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;

public interface ProductoUnidadDao extends GenericDao<ProductoUnidad, Integer>{
    
    
    public void addInConjunto(ProductoUnidad producto, int idConjunto) throws IOException;
    
    public void deleteInConjunto(ProductoUnidad producto, int idConjunt) throws IOException;
    
    public void updateInConjunto(ProductoUnidad producto, int idConjunt) throws IOException;
    
    public ProductoUnidad findByStockIdAndProductoId(int stock_id, int productoCompleto_id);
        
    
}
