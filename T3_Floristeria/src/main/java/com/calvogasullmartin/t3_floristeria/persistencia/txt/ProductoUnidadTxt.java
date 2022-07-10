package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import java.io.IOException;

public class ProductoUnidadTxt extends GenericDaoTxt<ProductoUnidad, Integer> implements ProductoUnidadDao{

    @Override
    public void addInConjunto(ProductoUnidad producto, int idConjunto) throws IOException {
        // Associar / poner, segun el id del conjuntoProductos, un nuevo producto con una determinada cantidad
    }

    

    
}
