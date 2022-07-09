package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorFuncionalVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;

public class LocalMenuControlador extends LocalControladorFuncional implements MenuControlador{

    public LocalMenuControlador(Estados estados) {
        super(estados);
    }        

    @Override
    public void introducirArticulo() {
         //pendiente el setEstado
    }

    @Override
    public void modificarUnidades() {
         //pendiente el setEstado
    }

    @Override
    public void retirarAriculo() {
         //pendiente el setEstado
    }

    @Override
    public void mostrarStock() {
         //pendiente el setEstado
    }

    @Override
    public void valorFloristeria() {
         //pendiente el setEstado
    }

    @Override
    public void venta() {
         //pendiente el setEstado
    }

    @Override
    public void historialVenta() {
         //pendiente el setEstado
    }

    @Override
    public void totalVentas() {
        //pendiente el setEstado
    }

    @Override
    public void exit() {
        this.setEstado(Estado.EXIT);
    }

    @Override
    public void aceptar(ControladorFuncionalVisitor controlador) {
        assert controlador != null;
        controlador.visitar(this);
    }
    
}
