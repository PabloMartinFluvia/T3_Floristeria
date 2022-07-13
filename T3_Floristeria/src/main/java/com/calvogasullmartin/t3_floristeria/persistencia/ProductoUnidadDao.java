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
    public void createProductoYAsociarloAlStockConUnidades(ProductoUnidad producto, int idConjunto) throws IOException;
    
    
    // read: obtener las unidades que hay de un producto en un determinado conjuno    
    public ProductoUnidad findByStockIdAndProductoId(int stock_id, int productoCompleto_id) throws IOException;
    // findAllByStockId:???? necesario???
    // NO: findAllByTiquetId -> no se requiere, ya que no se consulta un tiquet en concreto.
    
    //para saber si alguna vez se ha vendido (cosa que imposibilitat eliminar el registro productos)
    // -> ya el controlador se encargara de poner las unidades actuales en -1
    public int enCuantosTiquetsEsta(int producto_id) throws IOException;
    
    //update: modificar las unidades de un producto en un determinado stock (en un tiquet ya creado no se permite)
    public void actualizarUnidadesProductoByStockId(ProductoUnidad producto, int idConjunt) throws IOException;
    
    //delete: eliminar unidades y relación de este producto con un determinado stock
    //  **con un tiquet no es posible
    public void deleteInStock(ProductoUnidad producto, int idConjunt) throws IOException;
    //-> posible si no está asociado a ningun tiquet (nunca se ha vendido)
    //      -> si alguna vez se ha vendido hay que dejarlo, para no modificar el tiquet,
    //          -> però habrá que eliminar (mediante ProductoUnidadDao) la relación con el stock y las unidades en stock
    
    
    
    
    
    
    
    
    
    
    
    
}
