package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface AddProductoControlador extends ControladorPadre{
    
    public void seleccionarMenu(); //idem a todos los que salen del menú
    
    
    
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
    
    int getMaxCantidad();
}
