package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import java.io.IOException;

public class ProductoUnidadTxt extends GenericDaoTxt<ProductoUnidad, Integer> implements ProductoUnidadDao{

    @Override
    public void addInConjunto(ProductoUnidad producto, int idConjunto) throws IOException {
        // Associar / poner, segun el id del conjuntoProductos, un nuevo producto con una determinada cantidad
    }

    @Override
    public void deleteInConjunto(ProductoUnidad producto, int idConjunt) throws IOException {
        //sabiendo en que stock está: eliminar producto y cantidades
    }

    @Override
    public void updateInConjunto(ProductoUnidad producto, int idConjunt) throws IOException {
        //sabiendo en que stock está: actualizar /subsituir la cantidad de este ProductoUnidad
    }

    @Override
    public ProductoUnidad findByStockIdAndProductoId(int stock_id, int productoCompleto_id) {
        ProductoUnidad productoUnidad = null;
        /*
        sabiendo el id del producto i el id del stock en que esta: devolver el productoUnidad
        */
        return productoUnidad;
    }


    

    
}
