/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import java.io.IOException;

/**
 *
 * @author paumf
 */
public class ArrancarAppControlador extends ControladorFuncional{
    
    private Floristeria floristeria;

    public ArrancarAppControlador(Estado estado, Floristeria floristeria) {
        super(estado);
        this.floristeria = floristeria;
    }

    @Override
    public void controlar() {
        
    }
    
    public boolean isPrimeraVez(){
        return false;
    }
    
    public String getNombreFloristeria() throws IOException{
        return factory.getFloristeriaDao().findName();
    }
    
    public void setEstado(Estado estado){
        this.estado = estado;
    }
    
    public void guardarUnicaFloristeria (String nombre) throws IOException{
        floristeria.setId(1);
        floristeria.setNombre(nombre);
        floristeria.setValorTotal(0);
        factory.getFloristeriaDao().create(floristeria);
    }
    
    
}
