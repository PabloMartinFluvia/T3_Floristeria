package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.StocksC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.YesNoDialog;
import com.calvogasullmartin.t3_floristeria.utils.YesWaiting;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.MostrarConjuntoV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.modelos.CategoriaV;
import java.io.IOException;

public class StocksV extends MostrarConjuntoV{
    
    private InOut io;

    private StocksC controlador;
    
    public StocksV(StocksC controlador){
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interactuar(){        
        YesNoDialog requerimiento = new YesNoDialog("Desea visualizar las unidades");
        inicializar(controlador, requerimiento.read(), false);  //opcion mostrar stocks no muestra el id    
        try {
            readConjunto();
            mostrarConjuntos(controlador);           
        } catch (IOException ex) {
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV(controlador).isMore("consultar de nuevo");
    }

    @Override
    protected void readConjunto() throws IOException {
        int indexStock = new CategoriaV(true,"que stock(s) desea ver").pedirIndexCategoria();
        controlador.getStocks(indexStock);
    }
}
