package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import java.io.IOException;

public interface AddProductoControlador extends ControladorPadre{
    
    void inicializarNuevoProducto();
    
    void almacenarCategoria(Categoria categoria);
    
    void almacenarPrecio(float precio);
    
    void almacenarAltura(Altura altura);
    
    void almacenarColor(String color);
    
    void almacenarMaterial(Material material);
    
    void almacenarUnidadesIniciales(int cantidad);
    
    boolean isNuevo() throws IOException;
    
    void guardarProducto() throws IOException;
    
    String getInfoProductoSinNulls() throws IOException;
    
    void actualizarValoresStock() throws IOException;
    
    void seleccionarMenu();
}
