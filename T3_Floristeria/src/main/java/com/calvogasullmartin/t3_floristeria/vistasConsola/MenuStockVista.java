package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuStockVista {

    private Categoria categoria; // enum con todos los tipos de productos / stocks posibles
    //al ser un enum NO es un pecado que esta vista conozca información del modelo
    
    public MenuStockVista() {        
    }        
    
    public void interactuar(AddProductoControlador controlador){
        Categoria categoria = saberCategoria();
        new ProductoCompletoVista().interactuar(controlador, categoria);
        try {
            controlador.actualizarValoresStock();
        } catch (IOException ex) {
            // mensaje de error diciendo que no se ha podido actualizar el valor de la tienda ni del stock
        }
        finalizar(controlador);
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
    
    private void finalizar(AddProductoControlador controlador){
        boolean addMore = false;
        /*
        preguntarle si quiere añadir más productos, en caso afirmativo poner el addMore en true.
        */
        if (!addMore){
            controlador.seleccionarMenu();
        }
    }
}
