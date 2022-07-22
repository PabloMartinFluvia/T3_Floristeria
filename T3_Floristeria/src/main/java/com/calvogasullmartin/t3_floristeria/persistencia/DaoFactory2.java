package com.calvogasullmartin.t3_floristeria.persistencia;

public abstract class DaoFactory2 {
    
    protected static DaoFactory2 factory = null;

    public static DaoFactory2 getFactory() {
        assert factory != null;
        return DaoFactory2.factory;
    }
    
    public static void setFactory(DaoFactory2 factory) {
        assert factory != null;
        DaoFactory2.factory = factory;
    }    
    
    public abstract Conector2 getConector();
    
    public abstract FloristeriaDao2 getFloristeriaDao();
    
    public abstract ConjuntoProductosDao2 getConjuntoProductosDao();
    
    public abstract ProductoCompletoDao2 getProductoCompletoDao();
    
    public abstract ProductoUnidadDao2 getProductoUnidadDao();
}
