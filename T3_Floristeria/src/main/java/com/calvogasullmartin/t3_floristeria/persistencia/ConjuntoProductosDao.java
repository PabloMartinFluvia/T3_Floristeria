package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{

   
    /*
    c) push, dentro de floristeria.tiquets nuevo documento tiquet (id + valor + array de documentos
    {referencia al producto:, cantidad: }
    */
    public void createTiquet(ConjuntoProductos tiquet) throws IOException, SQLException;
    
    /*
    c) find floristeria y proyectar solo el documento que este en el indice de stocks coherente con el id de stock buscado
    */
    public ConjuntoProductos getOneStockById (int idStock)throws IOException, SQLException; 
    
    /*
    c) find floristeria y proyectar solo el stocks
    */
    public List<ConjuntoProductos> getAllStocks ()throws IOException, SQLException; 
    
    /*
    c) find floristeria y proyectar solo los tiquets
    */
    public List<ConjuntoProductos> getAllTiquets ()throws IOException, SQLException; 
    
    /*
    c) find flortisteria + set stpck.id.valor: {$incr: increment}
    */    
    public void incrementarValorEnStockById(int idStock, float incrementoValor) throws IOException, SQLException; 
}
