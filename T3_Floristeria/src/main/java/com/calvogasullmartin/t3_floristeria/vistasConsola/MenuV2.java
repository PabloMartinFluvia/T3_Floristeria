package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut2;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado2;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC2;

public class MenuV2 {
    
    private InOut2 io;
    
    private MenuC2 controlador;
    
    private final int MIN_OPTION = 0;

    public MenuV2(MenuC2 controlador) {
        io = new InOut2();
        this.controlador = controlador;
    }

    public void interactuar() {
        assert controlador != null;
        io.writeln("\n"+controlador.getMenuMensage());
        String mensage = "Seleccione una opción";
        int max = controlador.getNumOptionsNoExit();
        PedirEnteroLimitado2 solicitud = new PedirEnteroLimitado2(mensage,MIN_OPTION,max);
        int opcion = solicitud.read();
        indicarFuncionalidad(opcion);
    }
    
    //No me gusta este switch
    private void indicarFuncionalidad(int opcion){
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
