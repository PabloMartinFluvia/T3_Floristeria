package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import java.io.IOException;

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
                /*
                decir que guardado con exito
                */
            } else {
                /*
                decirle al usuario que no se puede guardar el producto (pk ya existe una igual guardado)
                 */
            }
        } catch (IOException ex) {
            // decir que no se puede consultar la BBDD
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
        float precio = 0f; // quitar el dar valor cuando se haga este metodo
        /*
        solicitar un precio valido (>0) y guardarlo en la variable local
         */
        return precio;
    }

    private Altura solicitarAltura() {
        Altura altura = null;// quitar el dar valor cuando se haga este metodo
        /*
        mostrar alturas y solicitar una altura válida y guardarla en la variable local
         */
        return altura;
    }

    private String solicitarColor() {
        String color = null;// quitar el dar valor cuando se haga este metodo
        /*
        solicitar una color valido y guardarla en la variable local
         */
        return color;
    }

    private Material solicitarMaterial() {
        Material material = null;// quitar el dar valor cuando se haga este metodo
        /*
        mostrar materiales y solicitar una material valido y guardarla en la variable local
         */
        return material;
    }
}
