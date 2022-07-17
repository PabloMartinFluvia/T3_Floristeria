package com.calvogasullmartin.t3_floristeria.controladores.Z_OLD;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppControlador;

public interface Z_OLD_MostrarConjuntoControlador extends PersistenciaControlador{
    
    public boolean isStock();
    
    public void setWithUnits(boolean withUnits);
    
    public boolean isWithUnits(); 
    
    public List<String> getAllConjuntos() throws IOException;
    
    public String getOneConjuntos(int conjundo_id) throws IOException;
    
    public void seleccionarMenu();
}
