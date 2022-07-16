package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.MenuControlador;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class MenuVista {
    
    private InOut io;

    public MenuVista() {
        io = new InOut();
    }

    public void interactuar(MenuControlador controlador) {
        assert controlador != null;
        io.writeln("\n"+controlador.getMenuMensage());
        String mensage = "Seleccione una opción";
        int max = controlador.getNumOptionsNoExit();
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado(mensage, 0,max);
        int opcion = solicitud.read();
        indicarFuncionalidad(opcion,controlador);
    }
    
    private void indicarFuncionalidad(int opcion, MenuControlador controlador){
        assert opcion >= 0 && opcion <= controlador.getNumOptionsNoExit();
        switch (opcion) {
            case 1:
                controlador.introducirArticulo();
                break;
            case 2:
                controlador.modificarUnidades();
                break;
            case 3:
                controlador.retirarAriculo();
                break;
            case 4:
                controlador.mostrarStock();
                break;
            case 5:
                controlador.valorFloristeria();
                break;
            case 6:
                controlador.venta();
                break;
            case 7:
                controlador.historialVenta();
                break;
            case 8:
                controlador.totalVentas();
                break;
            case 0:
                controlador.exit();
                break;
        }
    }        
}
