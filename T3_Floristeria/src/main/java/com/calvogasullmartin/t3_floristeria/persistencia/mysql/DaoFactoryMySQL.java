package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ValidadorBD;

public class DaoFactoryMySQL extends DaoFactory{        

    private ValidadorBD validador;
    
    private FloristeriaDao floristeriaDao;
    
    private ConjuntoProductosDao conjuntoProductosDao;
    
    private ProductoUnidadDao productoUnidadDao;
    
    private ProductoCompletoDao productoCompletoDao;
    
    public DaoFactoryMySQL(String[] argsConexion) {
        ConexionMySQL.setConexionValues(argsConexion);        
    }

    @Override
    public ValidadorBD getValidadorBD() {
        if (validador == null) {
            validador = new ValidadorBDMySQL();
        }
        return validador;
    }

    @Override
    public FloristeriaDao getFloristeriaDao() {
        if (floristeriaDao == null) {
            floristeriaDao = new FloristeriaMySQL();
        }
        return floristeriaDao;
    }

    @Override
    public ConjuntoProductosDao getConjuntoProductosDao() {
        if (conjuntoProductosDao == null) {
            conjuntoProductosDao = new ConjuntoProductosMySQL();
        }
        return conjuntoProductosDao;
    }

    @Override
    public ProductoCompletoDao getProductoCompletoDao() {
        if (productoCompletoDao == null) {
            productoCompletoDao = new ProductoCompletoMySQL();
        }
        return productoCompletoDao;
    }

    @Override
    public ProductoUnidadDao getProductoUnidadDao() {
        if (productoUnidadDao == null) {
            productoUnidadDao = new ProductoUnidadMySQL();
        }
        return productoUnidadDao;
    }    
}
