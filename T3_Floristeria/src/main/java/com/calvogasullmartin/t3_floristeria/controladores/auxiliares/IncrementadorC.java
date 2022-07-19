package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

public interface IncrementadorC {
    
    void resetIncr();
    
    int getMaxIncr();
    
    int getMinIncr();
    
    void setIncr(int incremento);
    
    void updateValorTotal();
    
    void updateValorStock();
}
