package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.CategoriaC;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.UnidadesC;

public interface NuevoC extends CategoriaC, UnidadesC{        
    
    void setNombre(String nombre);
    
    void setPrecio(float precio);
    
    void setAlturaIdx(int alturaIndex);
    
    void setColor(String color);
    
    void setMaterialIdx(int materialIndex);
    
    boolean estaEnStock() throws IOException;
    
    void guardarNuevoProductoUnidad() throws IOException;
}
