package com.CalvoGasullMArtin.T3_Floristeria.modelo;

import com.CalvoGasullMArtin.T3_Floristeria.utils.InOut;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Floristeria {
    
    private String nombre;    
    
    private List<Tiquet> tiquets;

    private List<Articulo> articulos;
    
    private float valor;

    public Floristeria() {
        this.nombre = new InOut().readString("Introducir nombre de la floristeria: ");
        this.tiquets = new LinkedList<>();
        this.articulos = new ArrayList<>();
        this.valor = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tiquet> getTiquets() {
        return tiquets;
    }

    public void setTiquets(List<Tiquet> tiquets) {
        this.tiquets = tiquets;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
        
}
