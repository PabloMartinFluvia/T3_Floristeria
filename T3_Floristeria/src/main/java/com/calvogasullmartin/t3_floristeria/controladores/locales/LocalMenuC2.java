package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalAppC2;
import com.calvogasullmartin.t3_floristeria.modelos.Estado2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.modelos.Menu2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;

public class LocalMenuC2 extends LocalAppC2 implements MenuC{

    private Menu2 menu;
    
    public LocalMenuC2(Manager2 manager) {
        super(manager);
        menu = new Menu2();
    }
    
    @Override
    public void aceptar(AppCVisitorC2 controlador) {
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
    public void selectNuevoArticulo() {
        this.setEstado(Estado2.NUEVO_PRODUCTO);
    }

    @Override
    public void selectModificarUnidades() {
        this.setEstado(Estado2.ACTUALIZAR_UNIDADES);
    }

    @Override
    public void selectDescatalogarArticulo() {
        this.setEstado(Estado2.RETIRAR_PRODUCTO);
    }

    @Override
    public void selectMostrarStocks() {
        this.setEstado(Estado2.MOSTRAR_STOCK);
    }

    @Override
    public void selectVerValor() {
        this.setEstado(Estado2.MOSTRAR_VALOR_TIENDA);
    }

    @Override
    public void selectNuevaVenta() {
        this.setEstado(Estado2.REALIZAR_VENTA);
    }

    @Override
    public void selectMostrarTiquets() {
        this.setEstado(Estado2.MOSTRAR_TIQUETS);
    }

    @Override
    public void selectVerFacturacion() {
        this.setEstado(Estado2.MOSTRAR_FACTURACION_TOTAL);
    }

    @Override
    public void seleccionarExit() {
        this.setEstado(Estado2.EXIT);
    }
    
}
