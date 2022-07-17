package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;

public interface ProductoUnidadDao extends GenericDao<ProductoUnidad, Integer>{
    
    //esta clase es para hacer operaciones CRUD con las cantidades de un producto
    //y establecer las relaciones de las unidades del producto con un conjunto(tique / stock) 
    
    //create: 
    //establecer las relaciones entre un producto y el conjunto al que se relacion + guardando unidades
        
    
    /*
    a)
    b) insert into producto el producto + insert into conjunto_has_products (isStock, producto_id, cantidad)
    c) insertOne producto en coleccion productos + crear documento {producto_id, cantidad} y hacer push en el array de stock.productos
    ***OBS: despúes de llamar a este metodo hay que llamar a incrementarValorFloristeria y a incrementarValorConjuntoById {con sus previos gets antes}
    */
    public void createProductoYAsociarloAlStockConUnidades(ProductoUnidad producto, int idConjunto) throws IOException; //ok
    
    
    // read: obtener las unidades que hay de un producto en un determinado conjuno    
    /*
    b) select cantidad form conjunto_has_product where stock_id & product id
    c) find conjunto by id + find producto by id + project candidad
    */
    public ProductoUnidad findByStockIdAndProductoId(int stock_id, int productoCompleto_id) throws IOException;
    // findAllByStockId:???? necesario???
    // NO: findAllByTiquetId -> no se requiere, ya que no se consulta un tiquet en concreto.
    
    //para saber si alguna vez se ha vendido (cosa que imposibilitat eliminar el registro productos)
    /*
    b) select from conjunto_has_products where producto_id & conjuno_id > nStocks + if elements from iterator > 0 return true
    c) agregate: find tiquet + look for product_id match + if documents size >0 return true
    */
    public boolean isVendidoAlgunaVezByProductId(int producto_id) throws IOException;
    
    //update: modificar las unidades de un producto en un determinado stock (en un tiquet ya creado no se permite)
    public void actualizarUnidadesProductoByStockId(ProductoUnidad producto, int idConjunto) throws IOException;//ok
    
    //delete: eliminar unidades y relación de este producto con un determinado stock
    //  **con un tiquet no es posible
    //  ** en el stock: solo si no se ha vendido alguna vez (para no perder la info del producto si está asociado a algun tiquet)
    /*
    b)delete in producto where id (and by cascade units will be deleted in conjunto_has_products
    c) delete document in producto Collection + and delete producto Document emebebed inside stocks in floristeria Collection
    */
    public void deleteInStock(ProductoUnidad producto, int idConjunt) throws IOException;    
    
    
    
    
    
    
    
    
    
    
    
    
}
