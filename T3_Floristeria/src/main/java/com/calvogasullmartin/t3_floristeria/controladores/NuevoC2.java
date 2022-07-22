package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.CategoriaC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementosC2;

public interface NuevoC2 extends CategoriaC2, IncrementosC2{        
    
    void setNombre(String nombre);
    
    void setPrecio(float precio);
    
    void setAlturaIdx(int alturaIndex);
    
    void setColor(String color);
    
    void setMaterialIdx(int materialIndex);
    
    boolean estaEnStock() throws IOException;
    
    void guardarNuevoProductoUnidad() throws IOException;
}
