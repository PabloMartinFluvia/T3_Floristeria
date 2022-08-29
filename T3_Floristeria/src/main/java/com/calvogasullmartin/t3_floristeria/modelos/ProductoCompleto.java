package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoCompleto {
    
    private int producto_id; 
    
    private Categoria categoria; 
    
    private String nombre;
    
    private float precio; 
    
    private Altura altura;
    
    private String color; 
    
    private Material material; 

    public ProductoCompleto() {        
        producto_id = 0;
        categoria = null;
        nombre = null;
        precio = 0;
        altura = null;
        color = null;
        material = null;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Altura getAltura() {
        return altura;
    }

    public void setAltura(Altura altura) {
        this.altura = altura;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    public int categoriaIndex(){
        return categoria.ordinal();
    }
    
    public String detallesProducto(){
        String detalles = null;
        switch(categoria){
            case ARBOL: 
                detalles = nombre+" (árbol), con altura "+altura+".";
                break;
            case FLOR: 
                detalles = nombre+" (flor), de color "+color+".";
                break;
            case DECORACION: 
                detalles = nombre+" (decoracion), de material "+material+".";
                break;
        }
        return detalles;
    }                        
}
