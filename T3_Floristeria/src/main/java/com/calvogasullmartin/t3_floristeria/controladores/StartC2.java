    package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ExitC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC2;

public interface StartC2 extends PersistenciaC2, ExitC2{
    
    public boolean isPrimeraVez() throws IOException;
    
    public void iniciarPersistencia() throws IOException;
    
    public void guardarUnicaFloristeria(String nombre) throws IOException;
        
    public String getNombreFloristeria() throws IOException;                 
}
