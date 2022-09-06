package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ShowConjuntosC extends PersistenciaC{   
    
    void readConjuntos() throws IOException, SQLException, MongoException; 
    
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
