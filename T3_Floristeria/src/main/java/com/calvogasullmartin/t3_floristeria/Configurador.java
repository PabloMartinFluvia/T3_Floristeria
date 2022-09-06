package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.vistas.Vista;
import com.calvogasullmartin.t3_floristeria.vistas.consola.ConsolaV;
import com.calvogasullmartin.t3_floristeria.controladores.Logica;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalLogica;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.mongo.DaoFactoryMongo;
import com.calvogasullmartin.t3_floristeria.persistencia.mysql.DaoFactoryMySQL;
import com.calvogasullmartin.t3_floristeria.persistencia.txt.DaoFactoryTxt;

public class Configurador {

    public void persistenciaTxt() {
        String rutaArchivoTxt = "floristeria.txt"; // archivo .txt ubicado en carpeta del proyecto
        DaoFactory.setFactory(new DaoFactoryTxt(rutaArchivoTxt));
    }

    public void persistenciaMySql() {
        String port = "3306";
        String user = "root";
        String password = "admin";
        String[] argsConexion = {port, user, password};
        DaoFactory.setFactory(new DaoFactoryMySQL(argsConexion));
    }

    public void persistenciaMongoDB() {
        //valores por defecto de una BD de mongo en local (sin autentificacion de user i password)
        String hostname = "localhost";
        int port = 27017;
        String uri = "mongodb://" + hostname + ":" + port;
        DaoFactory.setFactory(new DaoFactoryMongo(uri));
    }

    public Vista vistasConsola() {
        return new ConsolaV();
    }

    public Logica controladoresLocales() {
        return new LocalLogica();
    }
}
