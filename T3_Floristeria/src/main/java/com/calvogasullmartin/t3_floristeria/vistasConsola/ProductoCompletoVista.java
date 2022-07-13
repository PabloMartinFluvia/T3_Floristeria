package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import java.io.IOException;
import java.util.Scanner;

public class ProductoCompletoVista {
    
    
    private Categoria categoria;

    private AddProductoControlador controlador;

    public void interactuar(AddProductoControlador controlador, Integer conjunto_Id) {
               
        this.categoria = Categoria.values()[conjunto_Id-1];
        this.controlador = controlador;
        controlador.inicializarNuevoProducto(); // con unidades 0
        enviarToContoladorDatosComunes();
        enviarToControladorDatosEspecificos();        
        validarProducto();
    }

    private void enviarToContoladorDatosComunes() {
        //el id NO lo pedimos
        controlador.almacenarCategoria(categoria);
        controlador.almacenarPrecio(solicitarPrecio());
    }
    
    private float solicitarPrecio() {
        float precio = getFloat("Introduce el precio:");
        return precio;
    }
    
    private float getFloat(String frase) {        
        Scanner sc = new Scanner(System.in);
        float input;
        boolean ok = false;
        do{
            System.out.println(frase);
            //input = sc.nextFloat();
            input = Float.parseFloat(sc.nextLine());
            if(input>0){
                ok = true;
            }else{
                System.out.println("Introduce un precio positivo.");
            }
        }while(!ok);
        return input;
    }
    
    private void enviarToControladorDatosEspecificos() {
        switch (categoria) {
            case ARBOL:
                enviarToControladorDatosArbol();
                break;
            case FLOR:
                enviarToControladorDatosFlor();
                break;
            case DECORACION:
                enviarToControladorDatosDecoracion();
                break;
        }
    }

    private void enviarToControladorDatosArbol() {
        controlador.almacenarAltura(solicitarAltura());
    }
    
    private Altura solicitarAltura() {
        String mensaje = "Escoge altura: \n"
             + "\t1) Muy alto.\n"
             + "\t2) Alto.\n"
             + "\t3) Mediano.\n"
             + "\t4) Bajo.\n"  
             + "\t5) Muy bajo.\n";
        System.out.println(mensaje);
        int min = 1,max = 5;
        int respuesta = getInt(min,max);          
        Altura altura = getAltura(respuesta);
        return altura;
    }
    
    private int getInt(int min, int max) {
        Scanner sc = new Scanner(System.in);        
        int input;
        boolean ok=false;
        do{        
            System.out.println("Seleccione una opción entre "+min+" y "+max);
            input = sc.nextInt();
            if(input>= min && input<= max){                
                ok = true;
            }else{
                System.out.println("Error: opcion debe estar entre "+min+" y "+max);
            }
        }while(!ok);
        return input;
    }
    
    private Altura getAltura(int respuesta) {
        Altura altura = null;
        switch(respuesta) {
            case 1:
		altura = Altura.MUY_ALTO; break;
            case 2:
                altura = Altura.ALTO; break;
            case 3:
                altura = Altura.MEDIANO; break;
            case 4:
                altura = Altura.BAJO; break;
            case 5:
                altura = Altura.MUY_BAJO;
		}
        return altura;
    }
    
    private void enviarToControladorDatosFlor() {
        controlador.almacenarColor(solicitarColor());
    }

    private String solicitarColor() {
        String mensaje = "Introduce color: \n";
        System.out.println(mensaje);
        int min = 1,max = 30;
        return getString(min,max);
    }

    private String getString(int min, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean ok = false;
        do{
            input = sc.nextLine();
            if(input.length()>=min && input.length()<=max){
                ok = true;
            }else{
                System.out.println("Error: el número de caracteres debe estar entre "+min+" y "+max);
            }
        }while(!ok);
        return input;
    }
    
    private void enviarToControladorDatosDecoracion() {
        controlador.almacenarMaterial(solicitarMaterial());
    }
    
    private Material solicitarMaterial() {
        String mensaje = "Escoge material: \n"
            + "\t1) Madera.\n"
            + "\t2) Plastico.\n";
            int min = 1,max = 2;
            System.out.println(mensaje);
            int respuesta = getInt(min,max);
            Material material = getMaterial(respuesta);
        return material;
    }
    private Material getMaterial(int respuesta) {
        Material material = null;
        switch(respuesta) {
            case 1:
		material = Material.MADERA; break;
            case 2:
                material = Material.PLASTICO; break;            
		}
        return material;
    }
    
    private void validarProducto() {                
        try {            
            boolean es_nuevo = controlador.isNuevo();            
            if (es_nuevo) {
                new ProductoUnidadVista().interactuar(controlador);                
                controlador.addProductoConUnidadesEnStock();                
                System.out.println("Producto añadido con éxito.");///////InOut
            } else {
                System.out.println("Error: producto ya existente.");
            }
        } catch (IOException ex) {
            System.out.println("Base de datos inaxcesible.");
        }
    }          
}
