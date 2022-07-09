package com.calvogasullmartin.t3_floristeria.config;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactoryTxt;


public class Configurador{
          
    public void configurar() {        
        DaoFactory.setFactory(new DaoFactoryTxt());
    }
}
