package com.calvogasullmartin.t3_floristeria.controladores;

public interface ShowStocksC extends ShowConjuntosC, Controlador{
    
    void setIsWithUnits(boolean isWithUnits);
    
    void setIsAllOptionsPossible(boolean isAllOptionsPossible);
    
    boolean isAllOptionsPossible();
        
    void setIndexStockTarget(int indexStockTarget); //can be <0  if isAllOptionsPossible: true        
        
    void setIsWithID(boolean isWithUnits);
    
    int getIndexStockTarget(); 
}
