package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.vistas.Vista;
import com.calvogasullmartin.t3_floristeria.vistas.consola.ConsolaV;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalLogica;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.txt.DaoFactoryTxt;

public class Configurador{          
    
    public void persistenciaTxt() {
        String rutaArchivoTxt = "floristeria.txt"; // archivo .txt ubicado en src
        DaoFactory.setFactory(new DaoFactoryTxt(rutaArchivoTxt));
    }
    
    public Vista vistasConsola (){
        return new ConsolaV();
    }
    
    public Logica controladoresLocales (){
        return new LocalLogica();
    }
}
