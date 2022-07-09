package com.calvogasullmartin.t3_floristeria.controladores;

public interface ArrancarAppControladorInterface extends ControladorFuncional{
    
    //los métodos específicos de un ArrancarAppControlador
    
    public boolean isPrimeraVez();
    
    public void guardarUnicaFloristeria(String nombre);
    
    public String getNombreFloristeria();
}
