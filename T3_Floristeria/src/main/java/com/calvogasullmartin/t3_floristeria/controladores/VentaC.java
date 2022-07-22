package com.calvogasullmartin.t3_floristeria.controladores;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.SeleccionadorC;
import java.io.IOException;

public interface VentaC extends SeleccionadorC{
    
    public void resetTiquet();
    
    public void addProductoUnidadEnTiquet(int cantidadVendida);
    
    public void guardarTiquet() throws IOException;
}
