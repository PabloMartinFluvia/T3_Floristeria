package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.Conexion;
import java.io.File;
import java.io.IOException;

public class ConectorTxt implements Conexion{
    
    private static File archivoTxt;
            
    @Override
    public boolean isBDInicizialized() throws IOException{            
        return archivoTxt.exists() && !archivoTxt.isDirectory();
    }
    
    @Override
    public void initBD() throws IOException{        
        archivoTxt.createNewFile();
    }    

    public static void setPathTxt(String filePath) {
        ConectorTxt.archivoTxt = new File(filePath); 
    }
    
    public static File getArchivoTxt() {
        return ConectorTxt.archivoTxt;      
    }
}
