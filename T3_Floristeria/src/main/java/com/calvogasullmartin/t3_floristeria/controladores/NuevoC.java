package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.CategoriaC;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementadorC;
import java.io.IOException;

public interface NuevoC extends CategoriaC, IncrementadorC{
    
    void resetProductoUnidad();
    
    void setPrecio(float precio);
    
    void setAlturaIdx(int alturaIndex);
    
    void setColor(String golor);
    
    void setMaterialIdx(int materialIndex);
    
    boolean estaEnStock() throws IOException;
    
    void guardarProductoUnidad() throws IOException;
}
