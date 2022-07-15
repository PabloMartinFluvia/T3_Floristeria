package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;

public class LocalMenuControlador extends LocalControladorPadre implements MenuControlador{

    public LocalMenuControlador(Manager estados) {
        super(estados);
    }        

    @Override
    public void introducirArticulo() {
         this.setEstado(Estado.NUEVO_PRODUCTO);
    }

    @Override
    public void modificarUnidades() {
         this.setEstado(Estado.ACTUALIZAR_UNIDADES);
    }

    @Override
    public void retirarAriculo() {
         this.setEstado(Estado.RETIRAR_PRODUCTO);
    }

    @Override
    public void mostrarStock() {
         this.setEstado(Estado.MOSTRAR_STOCK);
    }

    @Override
    public void valorFloristeria() {
         this.setEstado(Estado.MOSTRAR_VALOR_TIENDA);
    }

    @Override
    public void venta() {
         this.setEstado(Estado.REALIZAR_VENTA);
    }

    @Override
    public void historialVenta() {
         this.setEstado(Estado.MOSTRAR_TIQUETS);
    }

    @Override
    public void totalVentas() {
        this.setEstado(Estado.MOSTRAR_FACTURACION_TOTAL);
    }

    @Override
    public void exit() {
        this.setEstado(Estado.EXIT);
    }

    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        assert controlador != null;
        controlador.visitar(this);
    }
    
}
