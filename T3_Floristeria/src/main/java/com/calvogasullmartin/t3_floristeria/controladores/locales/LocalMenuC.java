package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalAppC;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Menu;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;

public class LocalMenuC extends LocalAppC implements MenuC{

    private Menu menu;
    
    public LocalMenuC(Manager manager) {
        super(manager);
        menu = new Menu();
    }
    
    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public String getMenuMensage() {
        return menu.getMenuMensage();
    }

    @Override
    public int getNumOptionsNoExit() {
        return menu.getNumOpcionesSinExit();
    }

    @Override
    public void seleccionarNuevoProducto() {
        this.setEstado(Estado.NUEVO_PRODUCTO);
    }

    @Override
    public void seleccionarActualizarUnidades() {
        this.setEstado(Estado.ACTUALIZAR_UNIDADES);
    }

    @Override
    public void seleccionarRetirarProducto() {
        this.setEstado(Estado.RETIRAR_PRODUCTO);
    }

    @Override
    public void seleccionarMostrarStock() {
        this.setEstado(Estado.MOSTRAR_STOCK);
    }

    @Override
    public void seleccionarVerValorFloristeria() {
        this.setEstado(Estado.MOSTRAR_VALOR_TIENDA);
    }

    @Override
    public void seleccionarRealizarVenta() {
        this.setEstado(Estado.REALIZAR_VENTA);
    }

    @Override
    public void seleccionarMostrarTiquets() {
        this.setEstado(Estado.MOSTRAR_TIQUETS);
    }

    @Override
    public void seleccionarVerTotalFacturacion() {
        this.setEstado(Estado.MOSTRAR_FACTURACION_TOTAL);
    }

    @Override
    public void seleccionarExit() {
        this.setEstado(Estado.EXIT);
    }
    
}
