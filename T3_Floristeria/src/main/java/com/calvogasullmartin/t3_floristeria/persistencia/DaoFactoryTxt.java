package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.persistencia.txt.*;

public class DaoFactoryTxt extends DaoFactory{
    
    private Conector conector;
    
    private FloristeriaDao floristeriaDao;
    
    private ConjuntoProductosDao conjuntoProductosDao;
       

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

                 
}
