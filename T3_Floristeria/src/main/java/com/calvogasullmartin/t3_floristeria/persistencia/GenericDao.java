package com.calvogasullmartin.t3_floristeria.persistencia;

import java.io.IOException;

public interface GenericDao<T, ID> {        

    public void create(T entity) throws IOException;

    //public T read(ID id);

    /*
    public void update(T entity);

    public void deleteById(ID id);

    public List<T> findAll();
    */
}

