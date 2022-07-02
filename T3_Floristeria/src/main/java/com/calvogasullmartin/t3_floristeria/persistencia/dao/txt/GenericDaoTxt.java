package com.calvogasullmartin.t3_floristeria.persistencia.dao.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.dao.GenericDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GenericDaoTxt<T,ID> implements GenericDao<T, ID>{
    
    private ObjectMapper mapper;
    //chapuza ->
    private final String rutaArchivoTxt = "floristeria.txt";

    public GenericDaoTxt() {
        this.mapper = new ObjectMapper();
    }        

    @Override
    public void create(T entity) {
        // testeando, también incializa        
        try {
            //passar un objecte a json i escriure'l en un arxiu
            mapper.writeValue(new File(rutaArchivoTxt), entity);
        } catch (IOException ex) {
            System.err.println(ex);
        }
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
