package com.calvogasullmartin.t3_floristeria.persistencia.dao.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.dao.GenericDao;
import java.util.LinkedList;
import java.util.List;

public class GenericDaoTxt<T,ID> implements GenericDao<T, ID>{

    @Override
    public void create(T entity) {
        // Pendiente
    }

    @Override
    public T read(ID id) {
        T dto = null;
        //Pendiente
        return dto;
    }

    @Override
    public void update(T entity) {
        //Pendiente
    }

    @Override
    public void deleteById(ID id) {
        //Pendiente
    }

    @Override
    public List<T> findAll() {
        List<T> entidades = new LinkedList<>();
        //Pendiente
        return entidades;
    }    
}
