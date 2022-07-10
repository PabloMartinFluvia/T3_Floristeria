package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import java.util.List;

public class ProductoCompletoTxt extends GenericDaoTxt<ProductoCompleto, Integer> implements ProductoCompletoDao{

    @Override
    public List<ProductoCompleto> findAllByCategoria(Categoria categoria) {
        //pendent
    }
    
}
