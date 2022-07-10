package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import java.io.IOException;

public interface ConjuntoProductosDao extends GenericDao<ConjuntoProductos, Integer>{
        
    public void addToConjuntoValue(int idConjunto, float increment) throws IOException;
}
