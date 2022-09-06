package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface NuevoProductoC extends GestionarUnidadesC , Controlador{        
    
    void setCategoriaIdx(int categoriaIdx);
        
    void setNombre(String nombre);
    
    void setPrecio(float precio);
    
    void setAlturaIdx(int alturaIndex);
    
    void setColor(String color);
    
    void setMaterialIdx(int materialIndex);
    
    void lookForIdemProduct() throws IOException, SQLException, MongoException;
    
    boolean isIdemProductAlreadyInStocks();
    
    boolean isIdemProductSoldAnytime();
        
    void setIsNew(boolean esNuevo);                
}
