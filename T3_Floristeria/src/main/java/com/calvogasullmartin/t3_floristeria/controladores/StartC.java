    package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ExitC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaC;
import java.io.IOException;

public interface StartC extends AppC, PersistenciaC, ExitC{
    
    public boolean isPrimeraVez() throws IOException;
    
    public void iniciarPersistencia() throws IOException;
    
    public void guardarUnicaFloristeria(String nombre) throws IOException;
        
    public String getNombreFloristeria() throws IOException;                 
}
