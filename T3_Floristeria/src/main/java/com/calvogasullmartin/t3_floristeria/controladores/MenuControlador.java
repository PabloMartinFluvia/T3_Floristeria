package com.calvogasullmartin.t3_floristeria.controladores;

public interface MenuControlador extends AppControlador{
    
    public String getMenuMensage();
    
    public int getNumOptionsNoExit();
    
    public void introducirArticulo();
    
    public void modificarUnidades();
    
    public void retirarAriculo();
    
    public void mostrarStock();
    
    public void valorFloristeria();
    
    public void venta();
    
    public void historialVenta();
    
    public void totalVentas();
    
    public void exit();
}
