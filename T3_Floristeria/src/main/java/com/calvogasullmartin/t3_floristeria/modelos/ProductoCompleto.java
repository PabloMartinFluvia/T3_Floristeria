package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoCompleto {
    
    private int producto_id; //todos
    
    private Categoria categoria; //todos
    
    private float precio; //todos
    
    private Altura altura; //opcional
    
    private String color; //opcional
    
    private Material material; //opcional

    public ProductoCompleto() {
        // constructor -> todo null
        producto_id = 0;
        categoria = null;
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
    
    
    
    
    ///métodes problematics per a la persistencia
    public String obtainIntroProducto(){
        return "ID: "+producto_id+") ";
    }
    
    public String obtainDetallesProducto(){
        String detalles = null;
        switch(categoria){
            case ARBOL: 
                detalles = "Arbol con altura "+altura+". ";
                break;
            case FLOR: 
                detalles = "Flor de color "+color+". ";
                break;
            case DECORACION: 
                detalles = "Decoración de material "+material+". ";
                break;
        }
        return detalles;
    }
    
    
    public int obtainCategoriaIndex(){
        return categoria.ordinal();
    }
    
    
    public String toString(){
        String string = "";
        string = string + producto_id+", "+ precio + ", ";
        if(this.categoria == categoria.ARBOL){
            string = string + altura;
        }
        if(this.categoria == categoria.FLOR){
            string = string + color;
        }
        if(this.categoria == categoria.DECORACION){
            string = string + material;
        }
        return string;
    }
    
}
