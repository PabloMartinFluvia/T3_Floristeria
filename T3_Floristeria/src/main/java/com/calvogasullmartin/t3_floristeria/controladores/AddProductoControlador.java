package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import java.io.IOException;

public interface AddProductoControlador extends ControladorPadre{
    
    void inicializarNuevoProducto();
    
    void almacenarCategoria(int indexCategoria);
    
    void almacenarPrecio(float precio);
    
    void almacenarAltura(int  indexAltura);
    
    void almacenarColor(String color);
    
    void almacenarMaterial(int indexMaterial);
    
    void almacenarUnidadesIniciales(int cantidad);
    
    boolean isNuevo() throws IOException;
    
    void addProductoConUnidadesEnStock() throws IOException;
    
    void actualizarValoresStock() throws IOException;
    
    void seleccionarMenu();
    
    int getMaxCantidad();
}
