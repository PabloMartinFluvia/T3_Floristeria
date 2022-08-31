package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.utils.JsonManager;
import com.calvogasullmartin.t3_floristeria.persistencia.GenericDao;

public abstract class GenericDaoTxt<T, ID> implements GenericDao<T, ID> {

    protected JsonManager gestor;

    public GenericDaoTxt() {        
        gestor = new JsonManager(ValidadorBDTxt.getArchivoTxt());
    }
}
