package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalLogica;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactoryTxt;
import com.calvogasullmartin.t3_floristeria.vistasConsola.AppVista;


public class Configurador{
          
    public void persistenciaTxt() {        
        DaoFactory.setFactory(new DaoFactoryTxt());
    }
    
    public Vista vistasPorConsola (){
        return new AppVista();
    }
    
    public Logica controladoresLocales (){
        return new LocalLogica();
    }
}
