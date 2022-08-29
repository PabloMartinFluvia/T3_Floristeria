package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{

   
    /*
    a) genera el ID automaticamente
    b) insert into conjuntos (nuevo id + valor) 
        + insert into conjuntos_has_products varios registros uno por cada producto en el tiquet
        con la cantidad vendida
    c) push, dentro de floristeria.tiquets nuevo documento tiquet (id + valor + array de documentos
    {referencia al producto:, cantidad: }
    */
    public void createTiquet(ConjuntoProductos tiquet) throws IOException;
    
    /*
    a)
    b) join conjunto con conjunto_has_productos por el id del stock & join con productos con el id del producto
    c) find floristeria y proyectar solo el documento que este en el indice de stocks coherente con el id de stock buscado
    */
    public ConjuntoProductos getOneStockById (int idStock)throws IOException; //check
    
    /*
    a)
    b) hacer 3 veces el método anterior, uno para cada id
    c) find floristeria y proyectar solo el stocks
    */
    public List<ConjuntoProductos> getAllStocks ()throws IOException; //check
    
    /*
    a)
    b) hacer 2 joins entre conjunto - conjunto_products - products {excluyendo los conjunto_id de los stocks}
            segun cada conjunto_id(tiquet) crear cada tiquet de la lista
    c) find floristeria y proyectar solo los tiquets
    */
    public List<ConjuntoProductos> getAllTiquets ()throws IOException; //chek
    
    //update
    //incrementar (+/-) el valor del stock 
    //      no se puede hacer con un tiquet ya guardado
    /*
    b) update  conjunto set valor = valor + increment where stock_id
    c) find flortisteria + set stpck.id.valor: {$incr: increment}
    */    
    public void incrementarValorEnStockById(int idStock, float incremento) throws IOException; //check
}
