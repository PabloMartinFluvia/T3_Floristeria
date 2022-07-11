package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{
        
    public void addToConjuntoValue(int idConjunto, float increment) throws IOException;
    
    public List<ConjuntoProductos> findAllStocks ()throws IOException;
    
    public List<ConjuntoProductos> findAllTiquets ()throws IOException;
    
    public ConjuntoProductos findOneStockById (Integer id)throws IOException;
        
    public void createTiquet(ConjuntoProductos tiquet) throws IOException;
}
