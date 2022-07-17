package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.Z_OLD_ModificarProductoControlador;

public interface AppControladorVisitor {
        
    void visitar (InicioControlador controlador);
    
    void visitar(MenuControlador controlador);
    
    void visitar(NuevoProductoControlador controlador);
    
    void visitar (MostrarStocksControlador controlador);
    
    void visitar (IncrementarUnidadesControlador controlador);
    
    
    
    void visitar(Z_OLD_MostrarConjuntoControlador controlador);
    
    void visitar(MostrarTotalesControlador controlador);
    
    void visitar(Z_OLD_ModificarProductoControlador controlador);
    
    void visitar(NuevaVentaControlador controlador);
}
