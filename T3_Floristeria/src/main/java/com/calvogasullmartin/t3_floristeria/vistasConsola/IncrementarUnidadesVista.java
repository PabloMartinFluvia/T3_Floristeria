package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.StocksView;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.ProductoCompletoVista;
import com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos.StockVista;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.IncrementarUnidadesControlador;

public class IncrementarUnidadesVista extends StocksView {

    public IncrementarUnidadesVista() {
        io = new InOut();
    }

    public void interacturar(IncrementarUnidadesControlador controlador) {
        int stock_index = obtenerStockIndex("la familia del producto al que se desea actualizar las unidades");
        try {
            mostrarStocks(controlador, stock_index);
            boolean tryAgain = true;
            do {
                if (new StockVista().pedirIdProductoValido(controlador,"a actualizar")) {                   
                    int incremento = pedirIncrementoValido(controlador.getMinIncrement(),controlador.getMaxIncrement());
                    controlador.actualizarCantidad(incremento);
                    io.writeln("Cantidad del producto actualizado con éxito.");
                    tryAgain = false;
                } else {
                    tryAgain = ofrecerCancelar();
                }
            } while (tryAgain);
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        repetir("Desea modificar más unidades o volver al menú", controlador);
    }
    
    @Override
    protected int obtenerStockIndex(String mensage) {
        return new ProductoCompletoVista().pedirCategoria(mensage);
    }    
    
    private int pedirIncrementoValido(int min, int max){
        PedirEnteroLimitado requerimiento = new PedirEnteroLimitado("Introduzca en cuanto desea incrementar el stock (+/-)",min,max);
        return requerimiento.read();
    }

    private boolean ofrecerCancelar() {
        YesNoDialog requerimiento = new YesNoDialog("ID introducido no válido, desea intentar de nuevo");
        return requerimiento.read();
    }

    private void repetir(String mensage, IncrementarUnidadesControlador controlador) {
        YesNoDialog requerimiento = new YesNoDialog(mensage);
        if (!requerimiento.read()) {
            controlador.seleccionarMenu();
        }
    }
}
