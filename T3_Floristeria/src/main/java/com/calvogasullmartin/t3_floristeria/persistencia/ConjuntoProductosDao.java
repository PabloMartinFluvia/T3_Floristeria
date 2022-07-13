package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{
     
    //update
    //incrementar (+/-) el valor del stock 
    //      no se puede hacer con un tiquet ya guardado
    /*
    b) update  conjunto set valor = valor + increment where stock_id
    c) find flortisteria + set stpck.id.valor: {$incr: increment}
    */
    public void incrementarValorUnStockById(int idConjunto, float increment) throws IOException;
    
    
    public List<ConjuntoProductos> findAllStocks ()throws IOException;
    
    public List<ConjuntoProductos> findAllTiquets ()throws IOException;
    
    public ConjuntoProductos findOneStockById (Integer id)throws IOException;
        
    public void createTiquet(ConjuntoProductos tiquet) throws IOException;
    
}
