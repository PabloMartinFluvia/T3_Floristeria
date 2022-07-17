package com.calvogasullmartin.t3_floristeria.controladores.Z_OLD;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.PersistenciaControlador;
import java.io.IOException;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppControlador;

public interface NuevaVentaControlador extends PersistenciaControlador{
    
    public void inicializarTiquet();
    
    public void seleccionarMenu();
    
    public List<String> getAllStocks() throws IOException;
    
    public boolean isProductUnidadInSomeStock(int producto_id) throws IOException;
    
    public boolean isSuficienteStockParaLaVenta(int cantidad);
    
    public void addToTiquet();
    
    public void guardarTiquet() throws IOException;
    
    public void updateStockValues() throws IOException;
}
