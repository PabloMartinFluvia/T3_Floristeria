package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.persistencia.txt.*;

public class DaoFactoryTxt extends DaoFactory{
    
    private Conector conector;
    private FloristeriaDao floristeriaDao;
    private ProductoCompletoDao productoDao;
    private ProductoUnidadDao productoUnidadDao;
    private ConjuntoProductosDao conjuntoProductosDao;
    
    /*
    private StockDao stockDao;
    private TicketDao ticketDao;
    private ArbolDao arbolDao;
    private FlorDao florDao;
    private DecoracionDao decoracionDao;  
    */

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
    public ProductoCompletoDao getProductoCompletoDao() {
        if (productoDao == null) {
            productoDao = new ProductoCompletoTxt();
        }
        return productoDao;
    }
    
    @Override
    public ProductoUnidadDao getProductoUnidadesDao() {
        if (productoUnidadDao == null) {
            productoUnidadDao = new ProductoUnidadTxt();
        }
        return productoUnidadDao;
    }
    
    @Override
    public ConjuntoProductosDao getConjuntoProductosDao() {
       if (conjuntoProductosDao == null) {
            conjuntoProductosDao = new ConjuntoProductosTxt();
        }
        return conjuntoProductosDao; 
    }
    
    /*
    @Override
    public StockDao getStockDao() {
       if (stockDao == null) {
            stockDao = new StockDaoTxt();
        }
        return stockDao;
    }

    @Override
    public TicketDao getTicketDao() {
        if (ticketDao == null) {
            ticketDao = new TicketDaoTxt();
        }
        return ticketDao;
    }

    @Override
    public ArbolDao getArbolDao() {
        if (arbolDao == null) {
            arbolDao = new ArbolDaoTxt();
        }
        return arbolDao;
    }

    @Override
    public FlorDao getFlorDao() {
        if (florDao == null) {
            florDao = new FlorDaoTxt();
        }
        return florDao;
    }

    @Override
    public DecoracionDao getDecoracionDao() {
       if (decoracionDao == null) {
            decoracionDao = new DecoracionDaoTxt();
        }
        return decoracionDao;
    }   
    */   

    

    
}
