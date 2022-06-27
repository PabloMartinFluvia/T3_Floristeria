package com.calvogasullmartin.t3_floristeria.persistencia.dao;

public abstract class DaoFactory {
    
    //public?
    private static DaoFactory factory = null;

    public static DaoFactory getFactory() {
        assert factory != null;
        return factory;
    }

    public static void setFactory(DaoFactory factory) {
        assert factory != null;
        DaoFactory.factory = factory;
    }
    
    public abstract boolean isBDInizializada();

    public abstract void inicializarBD();        
    
    public abstract FloristeriaDao getFloristeriaDao();

    public abstract StockDao getStockDao();

    public abstract TicketDao getTicketDao();

    public abstract ArbolDao getArbolDao();

    public abstract FlorDao getFlorDao();
    
    public abstract DecoracionDao getDecoracionDao();
}
