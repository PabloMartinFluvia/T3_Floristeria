package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.persistencia.txt.*;

public class DaoFactoryTxt extends DaoFactory{
    
    private Conector conector;
    
    private FloristeriaDao floristeriaDao;
    
    private ConjuntoProductosDao conjuntoProductosDao;
    
    private ProductoUnidadDao productoUnidadDao;
    
    private ProductoCompletoDao productoCompletoDao;

    @Override
    public Conector getConector() {
       if (conector == null) {
            conector = new ConectorTxt();
        }
        return conector;
    }
    
    @Override
    public FloristeriaDao getFloristeriaDao() {
       if (floristeriaDao == null) {
            floristeriaDao = new FloristeriaTxt();
        }
        return floristeriaDao;
    }

    @Override
    public ConjuntoProductosDao getConjuntoProductosDao() {
        if (conjuntoProductosDao == null) {
            conjuntoProductosDao = new ConjuntoProductosTxt();
        }
        return conjuntoProductosDao;
    }    
    
    @Override
    public ProductoUnidadDao getProductoUnidadDao() {
        if (productoUnidadDao == null) {
            productoUnidadDao = new ProductoUnidadTxt();
        }
        return productoUnidadDao;
    }  
    
    @Override
    public ProductoCompletoDao getProductoCompletoDao() {
        if (productoCompletoDao == null) {
            productoCompletoDao = new ProductoCompletoTxt();
        }
        return productoCompletoDao;
    }
}
