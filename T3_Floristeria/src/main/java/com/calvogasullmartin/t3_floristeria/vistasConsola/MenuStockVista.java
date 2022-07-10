package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;

public class MenuStockVista {

    private Categoria categoria; // enum con todos los tipos de productos / stocks posibles
    //al ser un enum NO es un pecado que esta vista conozca información del modelo
    
    public MenuStockVista() {        
    }        
    
    public void interactuar(AddProductoControlador controlador){
        Categoria categoria = saberCategoria();
        new ProductoCompletoVista().interactuar(controlador, categoria);
        controlador.seleccionarMenu();
    }
    
    private Categoria saberCategoria(){
        mostrarCategorias();
        /*
        Preguntarle al usuario cual es la categoria del produco        
        */
        return pedirCategoria();
    }
    
    private void mostrarCategorias(){
        /*
        Mostrar por consola las distintas categorias de producto
        */
    }
    
    private Categoria pedirCategoria(){
        /*
        Pedir al usuario que introduzca cual es la categoria del producto (tiene que ser categoria valida)
        Poner el atributo categoria con el valor que elija.
        */
        return categoria;
    }
}
