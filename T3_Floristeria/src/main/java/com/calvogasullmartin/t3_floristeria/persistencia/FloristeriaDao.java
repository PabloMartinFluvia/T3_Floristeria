package com.calvogasullmartin.t3_floristeria.persistencia;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

public interface FloristeriaDao extends GenericDao<Floristeria, Integer>{
    
    public String findName() throws IOException;
    
    public void addToStockValue(float increment) throws IOException;
    
    public float findTotalStocks() throws IOException;
    
    public float findTotalTiquets() throws IOException;
}
