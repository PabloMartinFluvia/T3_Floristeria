package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import java.io.IOException;
import java.util.List;

public class ConjuntoProductosVista {

    private Categoria categoria; // enum con todos los tipos de productos / stocks posibles
    //al ser un enum NO es un pecado que esta vista conozca información del modelo
    
    private MostrarConjuntoControlador mostrarControlador;
    
    public ConjuntoProductosVista() {        
    }        
    
    public void interactuar(AddProductoControlador addProductoControlador){
        boolean isAllPosible = false;
        Integer conjunto_Id = saberStockId(isAllPosible);
        new ProductoCompletoVista().interactuar(addProductoControlador, conjunto_Id);
        try {
            addProductoControlador.actualizarValoresStock();
        } catch (IOException ex) {
            /*
            mensaje de error diciendo que no se ha podido actualizar el valor de la tienda ni del stock
            */
        }
        finalizar(addProductoControlador);
    }
    
    public void interactuar(MostrarConjuntoControlador mostrarControlador){
        this.mostrarControlador = mostrarControlador;
        mostrarControlador.setWithUnits(true); // por defecto se muestra el conjuno(s) con las unidades de los productos        
        if (!mostrarControlador.isStock()){
            mostrarTodo();
        }else {
            preguntarSiIncluirUnidades();
            Integer conjunto_id = saberStockId(true); // puede ser null si quiere ver todos los stocks
            mostrarConjuntoProductosSegunEleccion(conjunto_id);
        } 
        mostrarControlador.seleccionarMenu();
    }
    
    public void interactuar(ModificarProductoControlador modificarControlador){
        
    }
    
    private void mostrarConjuntoProductosSegunEleccion (Integer stock_id){
        if (stock_id == null){
            mostrarTodo();
        }else {
            mostrarUnConjunto(stock_id);
        }
    }
    
    private void mostrarUnConjunto (Integer conjunto_id){
        try {
            String conjuntoProductosUnidad = mostrarControlador.getOneConjuntos(conjunto_id);
            /*
            mostrar la lista de productos (en formato string)
            ** prguntandole al controlador.isWithUnits i .isStock se puede saber si son todos
               los tiquets o todos los stocks, i si incluyen unidades (los tiquets siempre la incluyen)
            ** Con Categoria.values()[conjunto_id-1] se puede saber qué estock es
            */
        } catch (IOException ex) {
            /*
            mostrarMensaje que no se ha podido consultar a la BBD
            */
        }
    }
    
    private void mostrarTodo(){
        try {
            List<String> tiquets_stocks = mostrarControlador.getAllConjuntos();
            /*
            mostrar la lista de listas de productos (en formato string)
            ** prguntandole al controlador.isWithUnits i .isStock se puede saber si son todos
            los tiquets o todos los stocks, i si incluyen unidades (los tiquets siempre la incluyen)
            ** son tiquets: cada List<String> es un tiquet -> List<List<String>> es una lista de todos los tiquets
            ** son stocks: cada List<String> es un stock -> List<List<String>> es una lista de todos los stocks {tamaño 3}
            */
        } catch (IOException ex) {
            /*
            mostrarMensaje que no se ha podido consultar a la BBD
            */
        }
    }
    
    private Integer saberStockId(boolean isAllPosible){
        mostrarOpciones(isAllPosible);
        return pedirOpcion(isAllPosible);
    }
    
    private void mostrarOpciones(boolean isAllPosible){
        /*
        Mostrar por consola las distintas categorias de producto.
        Si isAllPosible = true -> entonces también mostrar la opción TODOS (para que pueda seleccionar todos los stocks)
        */
    }
    
    private Integer pedirOpcion(boolean isAllPosible){
        Integer conjunto_id = null;
        /*
        Pedir al usuario que seleccione una opción de las mostradas [1:3] o [0:3]
        Poner la variable conjunto_id con el valor que elija.
            En el caso de isAllPosible = true -> si selecciona la opción TODOS devolver conjunto_id = null
        */
        return conjunto_id;
    }
    
    private void preguntarSiIncluirUnidades (){
        boolean incluirUnidades = true;
        /*
        preguntar al usuario si desea ver también las unidades de los productos en stock
        ** En untils hay una clase YesNoDialog que puede faiclitar esta consulta
        */
        mostrarControlador.setWithUnits(incluirUnidades);
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
