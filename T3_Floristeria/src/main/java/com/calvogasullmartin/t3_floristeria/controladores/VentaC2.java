package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC2;

public interface VentaC2 extends SeleccionadorC2{
    
    public void resetTiquet();
    
    public void addProductoUnidadEnTiquet(int cantidadVendida);
    
    public void guardarTiquet() throws IOException;
}
