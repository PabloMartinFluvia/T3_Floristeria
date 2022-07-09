package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ArrancarAppControlador extends ControladorFuncional{
    
    //los métodos específicos de un ArrancarAppControlador
    
    public boolean isPrimeraVez();
    
    public void iniciarPersistencia() throws IOException;
     
    public void iniciarFloristeria(String nombre) throws IOException;
    
    public void guardarUnicaFloristeria() throws IOException;
    
    public String getNombreFloristeria() throws IOException;
    
    public void seleccionarMenu();    
}
