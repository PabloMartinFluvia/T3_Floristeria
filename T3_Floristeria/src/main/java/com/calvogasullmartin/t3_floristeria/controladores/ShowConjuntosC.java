package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ShowConjuntosC extends PersistenciaC{   
    
    void readConjuntos() throws IOException; 
    
    void resetConjuntosReaded();
    
    int getNumConjuntosToShow();
    
    void setConjuntoToShow(int conjuntoIndex);
        
    String getTitle();
    
    float getValue();
    
    int getNumProductosToShow();        
    
    boolean isWithUnits();
        
    String getMensageCantidad(); 
    
    int getCantidad(int indexProducto);
            
    //puede devolver "" si isWithId = false;
    String getId(int indexProducto); 
    
    float getPrecio(int indexProducto);
    
    String getDetalles(int indexProducto); 
}
