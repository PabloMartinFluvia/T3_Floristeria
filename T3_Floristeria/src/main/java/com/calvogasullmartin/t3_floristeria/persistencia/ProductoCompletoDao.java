package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;
import java.sql.SQLException;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    /*
    No se sabe el id, solo las características, se busca tanto en stocks y en tickets (el primero que encuentre)    
    c) select id from productos where categoria = , nombre= , precio =, altura =, color=, material =
    */
    public int findId(ProductoCompleto producto) throws IOException, SQLException; 
    
    
    // read: obtener el producto  
    /*
    c) find form floristeria stocks.stock_id.productos where produto_id = + lookup with products
        and project only producto fields
    */
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws IOException, SQLException; 
    
    /*
    este método solo se llama cuando el producto no tiene relación con ningún ticket    
    c) deleteone in productos collection productoId=  + deleteMany in floristería matches productoId
    */
    public void eliminarProducto(ProductoCompleto producto) throws IOException, SQLException;
}
