package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalLogica2;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory2;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactoryTxt2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.AppV2;


public class Configurador{
          
    public void persistenciaTxt() {
        DaoFactory2.setFactory(new DaoFactoryTxt2());
    }
    
    public Vista vistasPorConsola (){
        return new AppV2();
    }
    
    public Logica controladoresLocales (){
        return new LocalLogica2();
    }
}
