package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LocalAddProductoControlador extends LocalControladorPadre implements AddProductoControlador {

    private ProductoCompleto producto;
    private ProductoUnidad productoUnidad;

    public LocalAddProductoControlador(Estados estados) {        
        super(estados);
    }

    @Override
    public void inicializarNuevoProducto() {
        producto = new ProductoCompleto();
        productoUnidad = new ProductoUnidad();
        productoUnidad.setProducto(producto);
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
    public boolean isNuevo() throws IOException {
        Categoria categoria = producto.getCategoria();
        List<ProductoCompleto> listaProductos = factory.getProductoCompletoDao()
                .getProductosSinUnidadesByStockId(categoria.ordinal()+1);
        if (listaProductos == null || listaProductos.isEmpty()){
            return true;
        }else{ // hay productos de esa categoria ya guardados
            listaProductos = seleccionarComun(listaProductos); //devuelve solo los que coinciden con el mismo precio
            switch (categoria) {
                case ARBOL: listaProductos = compararArboles(listaProductos); break;
                case FLOR: listaProductos = compararFlores(listaProductos); break;
                case DECORACION: listaProductos = compararDecoriaciones(listaProductos); break;                    
            }
            return listaProductos.size() == 0;
        }
    }

    private List<ProductoCompleto> seleccionarComun(List<ProductoCompleto> listaProductos) {  
        assert listaProductos != null;
        //no se compara el id, pk el usuario no introduce ids y no sabe como lo gestionamos
        List<ProductoCompleto> nuevaLista = new LinkedList<>();
        Iterator<ProductoCompleto> iterador = listaProductos.iterator();
        while (iterador.hasNext()){
            ProductoCompleto producto = iterador.next();
            if(this.producto.getPrecio() == producto.getPrecio()){
                nuevaLista.add(producto);
            }
        }
        return nuevaLista;
    }

    private List<ProductoCompleto> compararArboles(List<ProductoCompleto> listaProductos) {
        assert listaProductos != null;
        List<ProductoCompleto> nuevaLista = new LinkedList<>();
        Iterator<ProductoCompleto> iterador = listaProductos.iterator();
        while (iterador.hasNext()){
            ProductoCompleto producto = iterador.next();
            if(this.producto.getAltura().equals(producto.getAltura())){
                nuevaLista.add(producto);
            }
        }
        return nuevaLista;
    }

    private List<ProductoCompleto> compararFlores(List<ProductoCompleto> listaProductos) {
        assert listaProductos != null;
        List<ProductoCompleto> nuevaLista = new LinkedList<>();
        Iterator<ProductoCompleto> iterador = listaProductos.iterator();
        while (iterador.hasNext()){
            ProductoCompleto producto = iterador.next();
            if(this.producto.getColor().equals(producto.getColor())){
                nuevaLista.add(producto);
            }
        }
        return nuevaLista;
    }

    private List<ProductoCompleto> compararDecoriaciones(List<ProductoCompleto> listaProductos) {
        assert listaProductos != null;
        List<ProductoCompleto> nuevaLista = new LinkedList<>();
        Iterator<ProductoCompleto> iterador = listaProductos.iterator();
        while (iterador.hasNext()){
            ProductoCompleto producto = iterador.next();
            if(this.producto.getMaterial().equals(producto.getMaterial())){
                nuevaLista.add(producto);
            }
        }
        return nuevaLista;
    }

    @Override
    public void almacenarUnidadesIniciales(int cantidad) {
        assert cantidad > 0;
        productoUnidad.setCantidad(cantidad);
    }
    
    @Override
    public void addProductoConUnidadesEnStock() throws IOException {        
        factory.getProductoUnidadesDao().addNuevoProductoYAsociarloAlStockConUnidades(productoUnidad, getStockId());        
    }
    
    private int getStockId (){
        return producto.getCategoria().ordinal()+1;
    }

    @Override
    public void actualizarValoresStock() throws IOException {
        float incrementoValor = producto.getPrecio() * productoUnidad.getCantidad();
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);        
        factory.getConjuntoProductosDao().incrementarValorUnStockById(getStockId(), incrementoValor);
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
