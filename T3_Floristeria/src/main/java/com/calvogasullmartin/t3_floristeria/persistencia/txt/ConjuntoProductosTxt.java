package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import java.io.IOException;

public class ConjuntoProductosTxt extends GenericDaoTxt<ConjuntoProductos, Integer> implements ConjuntoProductosDao{

    @Override
    public void addToConjuntoValue(int idConjunto, float increment) throws IOException {
        // augmentar / reducir el valor del conjunto (que tenga ese id) segun esta cantidad
    }

}
