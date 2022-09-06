package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface GestionarUnidadesC extends PersistenciaC{      
    
    /*
    [0]: la màxima disminución = - cantidad actual
    [1]: el máximo augmento = cantidad máxima - cantidad actual
    */
    int[] getIncrementRange();
    
    /*
    el incremento és la variación de cantidades en el stock
    cada implementación hace cosas distintas:
        descatalogar: eliminar completamente el producto del stock
        modificar unidades: modificar las unidades en stock
        venta: modificar las unidades en stock + añadir el producto unidad que representa la venta en el ticket + incremento de valor del ticket
        nuevo: añade el prudcto al stock (eleccion del id segun si esNuevo)
    todos añaden al atributo variacionValor[] el canvio de valor debido a la modificación en el stock            
    */
    void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException;  
        
    void updateTotalsValues() throws IOException, SQLException, MongoException; 
    
    void resetProductUpdated();
}
