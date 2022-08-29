package com.calvogasullmartin.t3_floristeria.controladores;

public interface ShowStocksC extends ShowConjuntosC, Controlador{
    /*
     Los que implementen un ShowStocksC tienen en atributos que se
    inicializan en el constructor
            si es possible "seleccionar todos": false
                en la funcionalidad show stocks se permite -> se canvia mediante set
    
    también tiene como atributo un int para guardar el index del stock target
    */
    
    void setIsWithUnits(boolean isWithUnits);
    
    void setIsAllOptionsPossible(boolean isAllOptionsPossible);
    
    boolean isAllOptionsPossible();
        
    void setIndexStockTarget(int indexStockTarget); //can be <0  if isAllOptionsPossible: true        
    
    //que se lo dice al atributo del padre
    void setIsWithID(boolean isWithUnits);//solo se pone true (mostrar id) en las funcionalidades en que hay que elegir el producto    
    
    int getIndexStockTarget(); 
}
