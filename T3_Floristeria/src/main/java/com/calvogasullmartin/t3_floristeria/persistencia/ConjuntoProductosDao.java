package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{

    //update
    //incrementar (+/-) el valor del stock 
    //      no se puede hacer con un tiquet ya guardado
    /*
    b) update  conjunto set valor = valor + increment where stock_id
    c) find flortisteria + set stpck.id.valor: {$incr: increment}
    */    
    public void incrementarValorEnStockById(int idStock, float incremento) throws IOException; 
}
