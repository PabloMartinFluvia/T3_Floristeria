package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.modelos.Altura2;
import com.calvogasullmartin.t3_floristeria.modelos.Material2;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public class ProductoCompletoV2 {

    private InOut io;
    
    public ProductoCompletoV2() {
        io = new InOut();
    }
    
    public void mostrarProducto(ConjuntosC2 controlador, int[] indexs){
        String intro = controlador.getId(indexs); //puede ser "" si el controlador decide no mostrar-lo
        float precio = controlador.getPrecio(indexs);
        String detalles = controlador.getDetalles(indexs);
        io.writeln("\t"+intro+detalles+" Precio de "+precio+".");
    }
        
    public void requerirNuevoProducto(NuevoC2 controlador){           
        //el id no se pide, se encarga el dao que guarde el producto
        controlador.resetModels();
        int categoriaIdx = new CategoriaV2(false, "la familia del producto a añadir")
                .pedirIndexCategoria();        
        controlador.setCategoriaIdx(categoriaIdx);
        controlador.setNombre(pedirNombre());
        controlador.setPrecio(pedirPrecio());
        switch(categoriaIdx){
            case 0: controlador.setAlturaIdx(pedirAltura());break;
            case 1: controlador.setColor(pedirColor());break;
            case 2: controlador.setMaterialIdx(pedirMaterial());break;
        }
    }
    
    /**
     * estaria bien hacer un pedir String limitado en utils
     * @return 
     */
    private String pedirNombre() {
        String mensaje = "Introduzca un nombre/descripción:";
        int min = 5,max = 20;
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
    
    /*
    estaria bien hacer en utils un pedir float limitado
    */
    private float pedirPrecio(){
        float precio = 0;
        do{
            precio = io.readFloat("Introduzca el precio: ");
            if(precio <= 0){
                io.writeln("Error: precio debe ser > 0,");
            }
        }while(!(precio > 0));
        return precio;
    }
    /*
    estaria bien hacer en utils una clase similar a CategoriaV2, però que funcionara
    con cualquier clase de enum.
    */    
    private int pedirAltura(){
        int numOpciones = Altura2.values().length;
        io.writeln("Posibles alturas del árbol: ");
        for (int i = 0; i<numOpciones; i++){
            String opciones = "\t"+(i+1)+") "+Altura2.values()[i];
            io.writeln(opciones);
        }
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Seleccione opción",numOpciones);
        int opcion = solicitud.read();
        return opcion-1;
    }
    
    /**
     * estaria bien hacer un pedir String limitado en utils
     * @return 
     */
    private String pedirColor() {
        String mensaje = "Introduzca el color de la flor:";
        int min = 3,max = 20;
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
    
    /*
    estaria bien hacer en utils una clase similar a CategoriaV2, però que funcionara
    con cualquier clase de enum.
    */
    private int pedirMaterial(){
        int numOpciones = Material2.values().length;
        io.writeln("Posibles materiales de la decoriación: ");
        for (int i = 0; i<numOpciones; i++){
            String opciones = "\t"+(i+1)+") "+Material2.values()[i];
            io.writeln(opciones);
        }
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado("Seleccione opción",numOpciones);
        int opcion = solicitud.read();
        return opcion-1;
    }
}
