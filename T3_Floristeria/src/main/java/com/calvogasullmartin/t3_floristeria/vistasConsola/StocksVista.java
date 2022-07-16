package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesStockControlador;
import com.calvogasullmartin.t3_floristeria.controladores.StocksController;

public class StocksVista extends TodasOpcionCategoriaVista {

    public StocksVista() {
    }    

    public void mostrarStock(StocksController controlador, int stockIndex) {
        String titulo = controlador.getStockTitleInStocks(stockIndex);
        io.writeln("\n"+titulo);
        float value = controlador.getStockValueInStocks(stockIndex);
        io.writeln("El valor total es de: " + value);
        int numProductos = controlador.getNumProductsInStocks(stockIndex);
        for (int productoIndex = 0; productoIndex < numProductos; productoIndex++) {
            if (controlador.getCantidadProductoInStock(stockIndex, productoIndex) >= 0){ 
                if (controlador.isConUnidades()) {
                    new ProductoUnidadVista().muestraProductoUnidadInStocks(controlador, stockIndex, productoIndex);
                } else {
                    new ProductoCompletoVista().muestraProductoCompletoInStocks(controlador, stockIndex, productoIndex);
                }
            }else{
                //puede tener una cantidad negativa, cuando el usuario ha pedido descatalogar-lo,
                //pero aun sigue en la persistencia (pabla productos / colecció productos), debido a que 
                // está associado a algun tiquet antiguo
                // -> no mostrar
            }
        }
        io.writeln();
    }
    
    public boolean pedirIdProductoValido(IncrementarUnidadesStockControlador controlador, String mensage) {
        int producto_id = io.readInt("Introduzca el ID del producto ");
        return controlador.isIdValid(producto_id);
    }
}
