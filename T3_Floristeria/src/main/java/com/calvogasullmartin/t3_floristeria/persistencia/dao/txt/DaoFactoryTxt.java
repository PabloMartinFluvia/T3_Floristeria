package com.calvogasullmartin.t3_floristeria.persistencia.dao.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.dao.*;

public class DaoFactoryTxt extends DaoFactory{
    
    private FloristeriaDao floristeriaDao;
    private StockDao stockDao;
    private TicketDao ticketDao;
    private ArbolDao arbolDao;
    private FlorDao florDao;
    private DecoracionDao decoracionDao;
    

    public boolean isNecesarioInicializarBD(){
        //pendiente
        return false;
    }
    
    @Override
    public void inicializarBD() {
       //pendiente
    }

    @Override
    public FloristeriaDao getFloristeriaDao() {
       if (floristeriaDao == null) {
            floristeriaDao = new FloristeriaDaoTxt();
        }
        return floristeriaDao;
    }

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
    
}
