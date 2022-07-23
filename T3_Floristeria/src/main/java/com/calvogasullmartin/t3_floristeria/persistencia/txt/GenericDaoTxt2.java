package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.utils.JsonManager;
import com.calvogasullmartin.t3_floristeria.persistencia.GenericDao2;

public abstract class GenericDaoTxt2<T, ID> implements GenericDao2<T, ID> {

    protected JsonManager gestor;

    public GenericDaoTxt2() {
        gestor = new JsonManager(new ConectorTxt2().getArchivoTxt());
    }
}
