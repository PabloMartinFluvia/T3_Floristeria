package com.calvogasullmartin.t3_floristeria.controladores.auxiliares;

import java.io.IOException;

public interface UnidadesC extends PersistenciaC, NullC{        
    
    void resetIncr();
    
    void resetIncrValor();
    
    void resetIncrValorStocks();
    
    int getMaxIncr();
    
    int getMinIncr();
    
    void setIncr(int incremento);
    
    void addIncrValor();
    
    void addIncrValorStock();
    
    void updateValorTotal() throws IOException;
    
    void updateValoresStock() throws IOException;
    
    void setIncrsNull();
}
