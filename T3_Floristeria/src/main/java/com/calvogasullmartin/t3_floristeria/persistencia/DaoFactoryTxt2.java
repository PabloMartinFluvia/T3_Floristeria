package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.persistencia.txt.*;

public class DaoFactoryTxt2 extends DaoFactory2{
    
    private Conector2 conector;
    
    private FloristeriaDao2 floristeriaDao;
    
    private ConjuntoProductosDao2 conjuntoProductosDao;
    
    private ProductoUnidadDao2 productoUnidadDao;
    
    private ProductoCompletoDao2 productoCompletoDao;

    @Override
    public Conector2 getConector() {
       if (conector == null) {
            conector = new ConectorTxt2();
        }
        return conector;
    }
    
    @Override
    public FloristeriaDao2 getFloristeriaDao() {
       if (floristeriaDao == null) {
            floristeriaDao = new FloristeriaTxt2();
        }
        return floristeriaDao;
    }

    @Override
    public ConjuntoProductosDao2 getConjuntoProductosDao() {
        if (conjuntoProductosDao == null) {
            conjuntoProductosDao = new ConjuntoProductosTxt2();
        }
        return conjuntoProductosDao;
    }    
    
    @Override
    public ProductoUnidadDao2 getProductoUnidadDao() {
        if (productoUnidadDao == null) {
            productoUnidadDao = new ProductoUnidadTxt2();
        }
        return productoUnidadDao;
    }  
    
    @Override
    public ProductoCompletoDao2 getProductoCompletoDao() {
        if (productoCompletoDao == null) {
            productoCompletoDao = new ProductoCompletoTxt2();
        }
        return productoCompletoDao;
    }
}
