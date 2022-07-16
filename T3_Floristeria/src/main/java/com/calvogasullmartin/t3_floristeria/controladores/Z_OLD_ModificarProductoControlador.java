package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;

public interface Z_OLD_ModificarProductoControlador extends ControladorPadre{
    
    //ojo, pk hereda de MostrarConjuntoControlador (para heredar sus metodos). Dará problemas en accept / visit?
    
     public boolean IsUpdateUnits();
     
     public String getOneConjuntos(int conjundo_id) throws IOException;
     
     public void seleccionarMenu();
     
     public boolean isProductoIdValid(int producto_id);
     
     public void eliminarProductoUnidad() throws IOException;
     
     public void incrementarProductoUnidad(int incremento) throws IOException;
     
     public void disminuirValoresStock() throws IOException;
     
     public void actualizarValoresStock(int incremento) throws IOException;
     
     public int getUnidadesActuales();
}
