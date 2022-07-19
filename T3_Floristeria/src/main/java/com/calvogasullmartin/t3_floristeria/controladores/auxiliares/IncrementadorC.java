package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import java.io.IOException;

public interface IncrementadorC extends PersistenciaC{
    
    void resetIncr();
    
    void resetIncrValor();
    
    int getMaxIncr();
    
    int getMinIncr();
    
    void setIncr(int incremento);
    
    void addIncrValor();
    
    void updateValorTotal() throws IOException;
    
    void updateValorStock() throws IOException;
}
