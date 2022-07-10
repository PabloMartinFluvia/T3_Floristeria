package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;
import java.util.List;

public class LocalAddProductoControlador extends LocalControladorPadre implements AddProductoControlador{

    private ProductoCompleto producto;
    
    public LocalAddProductoControlador(Estados estados) {
        super(estados);
        producto = new ProductoCompleto();
    }        

    @Override
    public void inicializarNuevoProducto() {
        producto = new ProductoCompleto();
    }

    @Override
    public boolean isNuevo() {
        boolean esNuevo = false;
        List<ProductoCompleto> listaProductos = factory.getProductoCompletoDao()
                .findAllByCategoria(producto.getCategoria());
        //pendiente
        return esNuevo;
    }
    
    @Override
    public void almacenarUnidadesIniciales(int cantidad) {
        //pendiente
    }

    @Override
    public void guardarProducto() {
        //pendiente *recordar dar valor al id
    }
    
    @Override
    public void actualizarValoresStock() throws IOException {
        //pendiente: actualizar valor tienda y valor stock
    }

    @Override
    public String getInfoProductoSinNulls() {
        String productoToString = null;
        //pendiente
        return productoToString;
    }
    
    @Override
    public void almacenarCategoria(Categoria categoria) {
        producto.setCategoria(categoria);
    }

    @Override
    public void almacenarPrecio(float precio) {
        producto.setPrecio(precio);
    }

    @Override
    public void almacenarAltura(Altura altura) {
        producto.setAltura(altura);
    }

    @Override
    public void almacenarColor(String color) {
        producto.setColor(color);
    }

    @Override
    public void almacenarMaterial(Material material) {
        producto.setMaterial(material);
    }   

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }      
}
