package com.calvogasullmartin.t3_floristeria.zDescartes.modelos_xxx;

import java.util.List;

public class FloristeriaTest {
    
    private int floristeria_id;
    
    private String nombre;
    
    private float valorTotal;
    
    private List<GestorProductosTest> stockArboles;
    private List<GestorProductosTest> stockDecoracion;
    private List<GestorProductosTest> stockFlores;
    
    private List<List<GestorProductosTest>> tickets; // private List<GestorProductos> ticket;

    public FloristeriaTest(){
        
    }
    
    public FloristeriaTest(int floristeria_id, String nombre, float valorTotal) {
        this.floristeria_id = floristeria_id;
        this.nombre = nombre;
        this.valorTotal = valorTotal;
    }

    public int getFloristeria_id() {
        return floristeria_id;
    }

    public void setFloristeria_id(int floristeria_id) {
        this.floristeria_id = floristeria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
