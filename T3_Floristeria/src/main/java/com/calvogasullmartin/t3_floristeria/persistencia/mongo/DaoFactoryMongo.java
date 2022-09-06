package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ValidadorBD;

public class DaoFactoryMongo extends DaoFactory{
    
    private ValidadorBD validador;
    
    private FloristeriaDao floristeriaDao;
    
    private ConjuntoProductosDao conjuntoProductosDao;
    
    private ProductoUnidadDao productoUnidadDao;
    
    private ProductoCompletoDao productoCompletoDao;

    public DaoFactoryMongo(String uri) {
        ConexionMongo.setConexionValues(uri);        
    }

    @Override
    public ValidadorBD getValidadorBD() {
        if (validador == null) {
            validador = new ValidadorBDMongo();
        }
        return validador;
    }

    @Override
    public FloristeriaDao getFloristeriaDao() {
        if (floristeriaDao == null) {
            floristeriaDao = new FloristeriaMongo();
        }
        return floristeriaDao;
    }

    @Override
    public ConjuntoProductosDao getConjuntoProductosDao() {
        if (conjuntoProductosDao == null) {
            conjuntoProductosDao = new ConjuntoProductosMongo();
        }
        return conjuntoProductosDao;
    }

    @Override
    public ProductoCompletoDao getProductoCompletoDao() {
        if (productoCompletoDao == null) {
            productoCompletoDao = new ProductoCompletoMongo();
        }
        return productoCompletoDao;
    }

    @Override
    public ProductoUnidadDao getProductoUnidadDao() {
        if (productoUnidadDao == null) {
            productoUnidadDao = new ProductoUnidadMongo();
        }
        return productoUnidadDao;
    }
    
}
