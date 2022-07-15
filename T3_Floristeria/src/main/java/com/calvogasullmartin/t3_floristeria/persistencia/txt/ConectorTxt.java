package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.Conector;
import java.io.File;
import java.io.IOException;

public class ConectorTxt implements Conector{

    private final String rutaArchivoTxt;
    private File archivoTxt;

    public ConectorTxt() {
        rutaArchivoTxt = "floristeria.txt"; // se crea en el mismo directorio que carpetas src y target
        archivoTxt = new File(rutaArchivoTxt);        
    }
            
    @Override
    public boolean isBDInicizializada() throws IOException{       
        return archivoTxt.exists() && !archivoTxt.isDirectory();
    }

    @Override
    public void inicializarBD() throws IOException{
        archivoTxt.createNewFile();
    }    

    public File getArchivoTxt() {
        return archivoTxt;
    }        
}
