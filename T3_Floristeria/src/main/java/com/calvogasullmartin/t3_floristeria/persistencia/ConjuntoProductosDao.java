package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{
     
    /*
    a) add al array de tiquets
    b) crear registro en conjuntos (id + valor) + establecer relaciones entre 
        tiquet y los productoUnidad (qie lo haga producto unidad??)
    c) push al array de tiquets
    */
    /**
     * El objeto tiquet tiene todos los atributos con valores {valor total + lista de productosUnidad}
     * {el id al tiquet se le asigna aquí}
     * @param nuevoTiquet 
     * @throws java.io.IOException 
     */
    public void createNewTiquet(ConjuntoProductos nuevoTiquet) throws IOException;

    /*
    a)
    b) join conjunto con conjunto_has_productos por el id del stock & join con productos con el id del producto
    c) find floristeria y proyectar solo el documento que este en el indice de stocks coherente con el id de stock buscado
    */
    public ConjuntoProductos getOneStockById (int id)throws IOException;
    
    /*
    a)
    b) hacer 3 veces el método anterior, uno para cada id
    c) find floristeria y proyectar solo el stocks
    */
    public List<ConjuntoProductos> getAllStocks ()throws IOException;
    
    /*
    a)
    b) hacer 2 joins entre conjunto - conjunto_products - products {excluyendo los conjunto_id de los stocks}
            segun cada conjunto_id(tiquet) crear cada tiquet de la lista
    c) find floristeria y proyectar solo los tiquets
    */
    public List<ConjuntoProductos> getAllTiquets ()throws IOException;
    
    //update
    //incrementar (+/-) el valor del stock 
    //      no se puede hacer con un tiquet ya guardado
    /*
    b) update  conjunto set valor = valor + increment where stock_id
    c) find flortisteria + set stpck.id.valor: {$incr: increment}
    */    
    public void incrementarValorEnStockById(int idStock, float incremento) throws IOException;
   
    //no hay más updates, ya que canviar productos/unidades de productos asociados al stock se
    //encarga el dao PorudctoUnidad (que es quien establece relaciones y cantidad entre stock y producto)
    
    //delete: no se permite (no se puede eliminar un stock o un tiquet ya guardado)   
    
}
