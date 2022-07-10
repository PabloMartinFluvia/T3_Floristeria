package com.calvogasullmartin.t3_floristeria.persistencia;

public abstract class DaoFactory {
    
    protected static DaoFactory factory = null;

    public static DaoFactory getFactory() {
        assert factory != null;
        return DaoFactory.factory;
    }
    
    public static void setFactory(DaoFactory factory) {
        assert factory != null;
        DaoFactory.factory = factory;
    }    
    
    public abstract Conector getConector();
    
    public abstract FloristeriaDao getFloristeriaDao();
    
    public abstract ProductoCompletoDao getProductoCompletoDao();

    /*
    SOBRAN:
    habrá que crear métodos para los nuevos modelos
    
    public abstract StockDao getStockDao();

    public abstract TicketDao getTicketDao();

    public abstract ArbolDao getArbolDao();

    public abstract FlorDao getFlorDao();
    
    public abstract DecoracionDao getDecoracionDao();     
    */
}
