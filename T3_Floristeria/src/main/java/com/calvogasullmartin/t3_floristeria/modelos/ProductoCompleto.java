package com.calvogasullmartin.t3_floristeria.modelos;

public class ProductoCompleto {
    
    private Integer producto_id; //todos
    
    private Categoria categoria; //todos
    
    private Float precio; //todos
    
    private Altura altura; //opcional
    
    private String color; //opcional
    
    private Material material; //opcional

    public ProductoCompleto() {
        // constructor -> todo null
        producto_id = null;
        categoria = null;
        precio = null;
        altura = null;
        color = null;
        material = null;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
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
}
