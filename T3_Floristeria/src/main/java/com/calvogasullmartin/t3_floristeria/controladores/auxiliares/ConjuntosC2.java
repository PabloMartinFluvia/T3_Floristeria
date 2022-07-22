package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

public interface ConjuntosC2 extends NullC2{   
    
    void setConUnidades(boolean withUnits);
    
    void setConId(boolean withId);
 
    int getNumConjuntosToShow();
    
    String getTitle(int indexConjunto);
    
    float getValue(int indexConjunto);
    
    int getNumProductosToShow(int indexConjunto);
    
    int getCantidad(int[] indexs);
    
    boolean isConUnidades();
    
    String getMensageCantidad();    
    
    String getId(int[] indexs); //id opcional 
    
    float getPrecio(int[] indexs);
    
    String getDetalles(int[] indexs); //categoria + nombre/descripción + info particular
}
