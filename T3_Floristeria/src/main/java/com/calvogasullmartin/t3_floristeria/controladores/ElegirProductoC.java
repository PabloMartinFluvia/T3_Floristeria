package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface ElegirProductoC{       
        
    ShowStocksC getNewShowStocksC();
    
    boolean isStockEmpty();
        
    //si ok -> lo almacena en atributos
    boolean isIdValid(int productoId) throws IOException;    
         
}
