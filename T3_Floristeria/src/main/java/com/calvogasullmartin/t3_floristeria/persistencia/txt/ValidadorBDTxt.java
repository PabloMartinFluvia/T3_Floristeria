package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import java.io.File;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.ValidadorBD;

public class ValidadorBDTxt implements ValidadorBD{
    
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
        ValidadorBDTxt.archivoTxt = new File(filePath); 
    }
    
    public static File getArchivoTxt() {
        return ValidadorBDTxt.archivoTxt;      
    }
}
