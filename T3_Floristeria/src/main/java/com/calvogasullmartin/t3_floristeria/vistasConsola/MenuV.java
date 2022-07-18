package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

public class MenuV {
    
    private InOut io;
    
    private final int MIN_OPTION = 0;

    public MenuV() {
        io = new InOut();
    }

    public void interactuar(MenuC controlador) {
        assert controlador != null;
        io.writeln("\n"+controlador.getMenuMensage());
        String mensage = "Seleccione una opción";
        int max = controlador.getNumOptionsNoExit();
        PedirEnteroLimitado solicitud = new PedirEnteroLimitado(mensage,MIN_OPTION,max);
        int opcion = solicitud.read();
        indicarFuncionalidad(opcion,controlador);
    }
    
    //No me gusta este switch
    private void indicarFuncionalidad(int opcion, MenuC controlador){
        assert opcion >= 0 && opcion <= controlador.getNumOptionsNoExit();
        switch (opcion) {
            case 1:
                controlador.seleccionarNuevoProducto();
                break;
            case 2:
                controlador.seleccionarActualizarUnidades();
                break;
            case 3:
                controlador.seleccionarRetirarProducto();
                break;
            case 4:
                controlador.seleccionarMostrarStock();
                break;
            case 5:
                controlador.seleccionarVerValorFloristeria();
                break;
            case 6:
                controlador.seleccionarRealizarVenta();
                break;
            case 7:
                controlador.seleccionarMostrarTiquets();
                break;
            case 8:
                controlador.seleccionarVerTotalFacturacion();
                break;
            case 0:
                controlador.seleccionarExit();
                break;
        }
    }
}
