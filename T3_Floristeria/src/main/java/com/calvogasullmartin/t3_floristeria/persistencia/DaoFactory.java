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
    
    public abstract ConjuntoProductosDao getConjuntoProductosDao();
}
