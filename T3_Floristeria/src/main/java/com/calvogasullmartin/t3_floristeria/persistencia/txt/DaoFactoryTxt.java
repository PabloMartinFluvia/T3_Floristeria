package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.Conexion;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;

public class DaoFactoryTxt extends DaoFactory{   
    
    private Conexion conexion;
    
    private FloristeriaDao floristeriaDao;
    
    private ConjuntoProductosDao conjuntoProductosDao;
    
    private ProductoUnidadDao productoUnidadDao;
    
    private ProductoCompletoDao productoCompletoDao;        
        
    public DaoFactoryTxt(String filePath) {
        ConectorTxt.setPathTxt(filePath);
    }

    @Override
    public Conexion getConexion() {
       if (conexion == null) {
            conexion = new ConectorTxt();
        }
        return conexion;
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
