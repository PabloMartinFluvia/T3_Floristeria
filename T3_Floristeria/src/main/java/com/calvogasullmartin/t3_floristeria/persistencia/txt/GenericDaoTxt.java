package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.GenericDao;
import com.calvogasullmartin.t3_floristeria.utils.JsonManager;
import java.io.IOException;

public class GenericDaoTxt<T, ID> implements GenericDao<T, ID> {

    protected JsonManager gestor;

    public GenericDaoTxt() {
        gestor = new JsonManager(new ConectorTxt().getArchivoTxt());
    }

    @Override
    public void create(T entity) throws IOException{
        assert entity != null;
        gestor.writeInFile(entity); //appendable + nulls fields ignored
    }
    /*
    @Override
    public T read(ID id) {
        //pendiente
    }
    */
    
    /*
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
     */
}
