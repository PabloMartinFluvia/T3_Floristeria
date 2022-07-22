package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ExitC2;

public interface MenuC2 extends ExitC2, AppC2{
    
    public String getMenuMensage();
    
    public int getNumOptionsNoExit();
    
    public void seleccionarNuevoProducto();
    
    public void seleccionarActualizarUnidades();
    
    public void seleccionarRetirarProducto();
    
    public void seleccionarMostrarStock();
    
    public void seleccionarVerValorFloristeria();
    
    public void seleccionarRealizarVenta();
    
    public void seleccionarMostrarTiquets();
    
    public void seleccionarVerTotalFacturacion();
}
