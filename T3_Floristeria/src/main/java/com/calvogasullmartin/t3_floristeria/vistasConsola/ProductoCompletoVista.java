package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import java.io.IOException;
import java.util.Scanner;

public class ProductoCompletoVista {

    private Categoria categoria;

    private AddProductoControlador controlador;

    public void interactuar(AddProductoControlador controlador, Integer conjunto_Id) {
        assert controlador != null;
        assert categoria != null;
        this.categoria = Categoria.values()[conjunto_Id-1];
        this.controlador = controlador;
        controlador.inicializarNuevoProducto();
        enviarToContoladorDatosComunes();
        enviarToControladorDatosEspecificos();
        validarProducto();
    }

    private void validarProducto() {        
        try {
            if (controlador.isNuevo()) {
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

    private void enviarToContoladorDatosComunes() {
        //el id NO lo pedimos
        controlador.almacenarCategoria(categoria);
        controlador.almacenarPrecio(solicitarPrecio());
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

    private void enviarToControladorDatosFlor() {
        controlador.almacenarColor(solicitarColor());
    }

    private void enviarToControladorDatosDecoracion() {
        controlador.almacenarMaterial(solicitarMaterial());
    }

    private float solicitarPrecio() {
        float precio = getFloat("introduce el precio");
        return precio;
    }

    private Altura solicitarAltura() {
        String mensaje = "Escoge altura: \n"
             + "\t1) Muy alto.\n"
             + "\t2) Alto.\n"
             + "\t3) Mediano.\n"
             + "\t4) Bajo.\n"  
             + "\t5) Muy bajo.\n";
             int min = 1,max = 5;
             int respuesta = getInt(mensaje,min,max);
        Altura altura = getAltura(respuesta);
        return altura;
    }

    private Altura getAltura(int respuesta) {
        Altura altura;
        switch(respuesta) {
			case 1:
				altura = Altura.MUY_ALTO;
			case 2:
                altura = Altura.ALTO;
			case 3:
                altura = Altura.MEDIANO;
			case 4:
                altura = Altura.BAJO;
            default:
                altura = Altura.MUY_BAJO;
		}
        return altura;
    }

    private int getInt(String mensaje, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int input;
        do{
            System.out.println(mensaje);
            input = sc.nextInt();
        }while(input>=min && input<=max);
        return input;
    }

    private String solicitarColor() {
        String mensaje = "Introduce color: \n";
        int min = 3,max = 10;
        return getString(mensaje,min,max);
    }

    private String getString(String mensaje, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        do{
            System.out.println(mensaje);
            input = sc.nextLine();
        }while(input.length()>=min && input.length()<=max);
        return input;
    }

    private Material solicitarMaterial() {
        String mensaje = "Escoge material: \n"
            + "\t1) Madera.\n"
            + "\t2) Plastico.\n";
            int min = 1,max = 2;
            int respuesta = getInt(mensaje,min,max);
            Material material = getMaterial(respuesta);
        return material;
    }
    private Material getMaterial(int respuesta) {
        Material material;
        switch(respuesta) {
			case 1:
				material = Material.MADERA;
            case 2:
                material = Material.PLASTICO;
            default:
                material = Material.MADERA;
		}
        return material;
    }
    private float getFloat(String frase) {
		System.out.println(frase);
        Scanner sc = new Scanner(System.in);
        float input;
        do{
            input = sc.nextFloat();
        }while(input>0);
        return input;
    }


}
