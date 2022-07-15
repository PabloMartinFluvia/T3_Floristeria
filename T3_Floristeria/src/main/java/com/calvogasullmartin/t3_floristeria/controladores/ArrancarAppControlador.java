package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ArrancarAppControlador extends ControladorPadre{
    
    //los métodos específicos de un ArrancarAppControlador
    
    public boolean isPrimeraVez() throws IOException;
    
    public void iniciarPersistencia() throws IOException;
    
    public void guardarUnicaFloristeria(String nombre) throws IOException;
        
    public String getNombreFloristeria() throws IOException;
    
    public void seleccionarMenu();    
    
    public void seleccionarExit();
}
