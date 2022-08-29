package com.calvogasullmartin.t3_floristeria.vistas.consola.modelos;

import com.calvogasullmartin.t3_floristeria.controladores.ShowConjuntosC;
import com.calvogasullmartin.t3_floristeria.controladores.NuevoProductoC;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class ProductoCompletoV {

    private final InOut io;
    
    public ProductoCompletoV() {
        io = new InOut();
    }
    
    public void mostrarProducto(ShowConjuntosC controlador, int indexProducto){
        assert controlador != null;
        assert indexProducto >=0 && indexProducto < controlador.getNumProductosToShow();
        String intro = controlador.getId(indexProducto); //puede ser "" si el controlador decide no mostrar-lo
        float precio = controlador.getPrecio(indexProducto);
        String detalles = controlador.getDetalles(indexProducto);
        io.writeln("\t"+intro+detalles+" Precio de "+precio+".");
    }
    
        
    public void askForNewProduct(NuevoProductoC controlador){     
        assert controlador != null;
        //el id no se pide, se encarga el dao que guarde el producto        
        int categoriaIdx = new CategoriaV()
                .askForCategoriaIndex(false, "la familia del producto a añadir");        
        controlador.setCategoriaIdx(categoriaIdx);
        controlador.setNombre(askNombre());        
        switch(categoriaIdx){
            case 0 -> controlador.setAlturaIdx(askAltura());
            case 1 -> controlador.setColor(askColor());
            case 2 -> controlador.setMaterialIdx(askMaterial());
        }
        controlador.setPrecio(askPrecio());
    }
    
    private String askNombre() {
        String message = "Introduzca un nombre/descripción:";
        int min = 5,max = 20;
        String input;
        boolean ok = false;
        do{
            input = io.readString(message);
            if(input.length()>=min && input.length()<=max){
                ok = true;
            }else{
                System.out.println("Error: el número de caracteres debe estar entre "+min+" y "+max);
            }
        }while(!ok);
        return input;
    }
    
    private float askPrecio(){
        float precio = 0;
        do{
            precio = io.readFloat("Introduzca el precio: ");
            if(precio <= 0){
                io.writeln("Error: precio debe ser > 0,");
            }
        }while(!(precio > 0));
        return precio;
    }
        
    private int askAltura(){
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
        
    private String askColor() {
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
    
    private int askMaterial(){
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
}
