package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;

public interface NuevoProductoControlador extends PersistenciaControlador{
        
    void inicializarNuevoProducto();
    
    void almacenarCategoria(int indexCategoria);
    
    void almacenarPrecio(float precio);
    
    void almacenarAltura(int  indexAltura);
    
    void almacenarColor(String color);
    
    void almacenarMaterial(int indexMaterial);
    
    void almacenarUnidadesIniciales(int cantidad);
    
    boolean isNuevo() throws IOException;
    
    void addProductoConUnidadesEnStock() throws IOException;
    
    void incrementarValores() throws IOException;        
    
    int getMaxCantidad();
}
