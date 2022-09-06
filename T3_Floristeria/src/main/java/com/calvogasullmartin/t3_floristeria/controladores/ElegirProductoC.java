package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ElegirProductoC{       
        
    ShowStocksC getNewShowStocksC();
    
    boolean isStockEmpty();
        
    //si ok -> lo almacena en atributos
    boolean isIdValid(int productoId) throws IOException, SQLException, MongoException;    
         
}
