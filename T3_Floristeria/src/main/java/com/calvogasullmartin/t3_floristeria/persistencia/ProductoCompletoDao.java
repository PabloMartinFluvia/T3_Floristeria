package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    /*
    No se sabe el id, solo las características, se busca tanto en stocks y en tickets (el primero que encuentre)
    b) select id from productos where categoria = , nombre= , precio =, altura =, color=, material =
    c) idem
    */
    public int findId(ProductoCompleto producto) throws IOException; //check
    
    
    // read: obtener el producto  
    /*
    b) select producto columns from projuctos join conjunto_has_productos where id_p= && id_s=
    c) find form floristeria stocks.stock_id.productos where produto_id = + lookup with products
        and project only producto fields
    */
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws IOException; //check
    
    /*
    este método solo se llama cuando el producto no tiene relación con ningún ticket
    
    b) delete from products where productoId=  (y por cascade se elimina la relación con stocks (y tickets, aunque no está
    programado para que solo se llame a esté metodo si no está asociado a ningun ticket).
    c) deleteone in productos collection productoId=  + deleteMany in floristería matches productoId
    */
    public void eliminarProducto(ProductoCompleto producto) throws IOException;
}
