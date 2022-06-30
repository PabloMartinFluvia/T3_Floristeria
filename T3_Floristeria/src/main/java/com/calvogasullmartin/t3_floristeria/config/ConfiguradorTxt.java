package com.calvogasullmartin.t3_floristeria.config;

import com.calvogasullmartin.t3_floristeria.persistencia.dao.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.dao.txt.DaoFactoryTxt;
import java.io.File;



public class ConfiguradorTxt implements Configurador{
    
    //provisional, se guarda dentro de la carpeta src
    private final String rutaArchivoTxt = "floristeria.txt";

    @Override
    public void configurarPersistenciaFactory() {
        // de momento se inyecta todo "a puro codigo" en un solo método, solo para que funcione
        DaoFactory.setFactory(new DaoFactoryTxt());
    }

    @Override
    public boolean validarBD() {
       File archivo = new File(rutaArchivoTxt);
       if (archivo.exists() && !archivo.isDirectory()){
           return true;
       }else {
           // mensaje de error: ruta del archivo no es valido
           //mejor que pete el programa, o que canvie la ruta del archivo (y canviar el "final")
           //etc... habrá que pensar un rato que hacer
           return false;
       }
    }

    

    
    
    
    
}
