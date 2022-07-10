package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.util.List;

public interface ProductoCompletoDao extends GenericDao<ProductoCompleto, Integer>{
    
    public List<ProductoCompleto> findAllByCategoria(Categoria categoria);
}
