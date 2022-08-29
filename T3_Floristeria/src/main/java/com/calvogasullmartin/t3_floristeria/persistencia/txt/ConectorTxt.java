package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import java.io.File;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.Conector;

public class ConectorTxt implements Conector{

    private final String rutaArchivoTxt = "floristeria.txt"; // se crea en el mismo directorio que carpetas src y target
    
    private final File archivoTxt;

    public ConectorTxt() {
        archivoTxt = new File(rutaArchivoTxt);        
    }
            
    @Override
    public boolean isBDInicizialized() throws IOException{            
        return archivoTxt.exists() && !archivoTxt.isDirectory();
    }
    
    @Override
    public void initBD() throws IOException{        
        archivoTxt.createNewFile();
    }    
    
    public File getArchivoTxt() {
        return archivoTxt;      
    }
}
