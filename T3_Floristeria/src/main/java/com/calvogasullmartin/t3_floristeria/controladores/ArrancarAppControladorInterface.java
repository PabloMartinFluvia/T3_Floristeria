package com.calvogasullmartin.t3_floristeria.controladores;

public interface ArrancarAppControladorInterface extends ControladorFuncionalInterface{
    
    //los métodos específicos de un ArrancarAppControlador
    
    public boolean isPrimeraVez();
    
    public void iniciarPersistencia();
     
    public void iniciarFloristeria(String nombre);
    
    public void guardarUnicaFloristeria();
    
    public String getNombreFloristeria();
    
    public void seleccionarMenu();    
}
