package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarStocksControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class ProductoCompletoVista extends CategoriaVista{        
    
    private Categoria categoria;

    private AddProductoControlador controlador;

    public ProductoCompletoVista() {        
    }
        
    public void requerirNuevoProducto(AddProductoControlador controlador){   
        //el id no se pide
        int index = pedirCategoria("la familia del producto a añadir");
        controlador.almacenarCategoria(index);
        controlador.almacenarPrecio(io.readFloat("Introduzca el precio: "));
        switch(index){
            case 0: controlador.almacenarAltura(pedirAltura());break;
            case 1: controlador.almacenarColor(pedirColor());break;
            case 2: controlador.almacenarMaterial(pedirMaterial());break;
        }
    }
    
    @Override
    protected int ofrecerOpcionTodos() {
        return 1;
    }
    
    //devuelve indice en el enum
    private int pedirAltura(){
        int numOpciones = Altura.values().length;
        io.writeln("Posibles alturas del árbol: ");
        for (int i = 0; i<numOpciones; i++){
            String opciones = "\t"+(i+1)+") "+Altura.values()[i];
            io.writeln(opciones);
        }
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Seleccione opción",numOpciones);
        int opcion = solicitud.read();
        return opcion-1;
    }
    
    private String pedirColor() {
        String mensaje = "Introduzca el color de la flor:";
        int min = 1,max = 20;
        String input;
        boolean ok = false;
        do{
            input = io.readString(mensaje);
            if(input.length()>=min && input.length()<=max){
                ok = true;
            }else{
                System.out.println("Error: el número de caracteres debe estar entre "+min+" y "+max);
            }
        }while(!ok);
        return input;
    }
    
    //devuelve indice en el enum
    private int pedirMaterial(){
        int numOpciones = Material.values().length;
        io.writeln("Posibles materiales de la decoriación: ");
        for (int i = 0; i<numOpciones; i++){
            String opciones = "\t"+(i+1)+") "+Material.values()[i];
            io.writeln(opciones);
        }
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Seleccione opción",numOpciones);
        int opcion = solicitud.read();
        return opcion-1;
    }   
    
    public void muestraProductoCompletoInStocks(MostrarStocksControlador controlador, int stockIndex, int productoIndex){
        String intro = controlador.getIntroProductoInStock(stockIndex, productoIndex);
        float precio = controlador.getPrecioProductoInStock(stockIndex, productoIndex);
        String detalles = controlador.getDetallesProductoInStock(stockIndex, productoIndex);
        io.writeln("\t"+intro+"Tiene un precio de "+precio+". "+detalles);
    }
}
