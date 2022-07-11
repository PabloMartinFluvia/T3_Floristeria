package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import java.util.List;

public interface NuevaVentaControlador extends ControladorPadre{
    
    public void inicializarTiquet();
    
    public void seleccionarMenu();
    
    public List<String> getAllStocks() throws IOException;
    
    public boolean isProductUnidadInSomeStock(int producto_id) throws IOException;
    
    public boolean isSuficienteStockParaLaVenta(int cantidad);
    
    public void addToTiquet();
    
    public void guardarTiquet() throws IOException;
    
    public void updateStockValues() throws IOException;
}
