package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;

public class ProductoUnidadVista {

    AddProductoControlador controlador;
            
    public ProductoUnidadVista() {
    }
    
    public void interactuar (AddProductoControlador controlador){
        this.controlador = controlador;
        enviarToContoladorUnidadesIniciales();
    }
    
    private void enviarToContoladorUnidadesIniciales() {        
        controlador.almacenarUnidadesIniciales(pedirUnidadesIniciales());
    }
    
    private int pedirUnidadesIniciales(){
        int unidades = 0; // quitar asignacion de valor al implementar el método
        /*
        preguntar cuantas unidades iniciales tendrá este nuevo producto
        Se le puede dar la opción que por defecto sea una
        */
        return unidades;
    }        
}
