package com.calvogasullmartin.t3_floristeria.config;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.txt.DaoFactoryTxt;


public class Configurador{
          
    public void inyectarFactory() {        
        DaoFactory.setFactory(new DaoFactoryTxt());
    }
}
