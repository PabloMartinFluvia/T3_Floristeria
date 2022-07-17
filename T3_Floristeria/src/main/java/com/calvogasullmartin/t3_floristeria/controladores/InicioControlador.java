package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;

public interface InicioControlador extends PersistenciaControlador{
    
    //los métodos específicos de un InicioControlador
    
    public boolean isPrimeraVez() throws IOException;
    
    public void iniciarPersistencia() throws IOException;
    
    public void guardarUnicaFloristeria(String nombre) throws IOException;
        
    public String getNombreFloristeria() throws IOException;
    
    public void seleccionarMenu();    
    
    public void seleccionarExit();
}
