package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import java.util.List;

public interface MostrarConjuntoControlador extends ControladorPadre{
    
    public boolean isIsStock();
    
    public void setWithUnits(boolean withUnits);
    
    public boolean isWithUnits(); 
    
    public List<List<String>> getAllConjuntos() throws IOException;
    
    public List<String> getOneConjuntos(int conjundo_id) throws IOException;
    
    public void seleccionarMenu();
}
