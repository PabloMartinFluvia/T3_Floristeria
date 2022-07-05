package com.calvogasullmartin.t3_floristeria.config;

import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import com.calvogasullmartin.t3_floristeria.persistencia.txt.DaoFactoryTxt;


public class Configurador{
          
    public void inyectarFactory() {        
        DaoFactory.setFactory(new DaoFactoryTxt());
    }
    
    //provisional, se guarda dentro de la carpeta src
    //private final String rutaArchivoTxt = "floristeria.txt";
    
    /*
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
    */
    
    /*
    public String getRutaArchivoTxt() {
        return rutaArchivoTxt;
    }  
    */
}
