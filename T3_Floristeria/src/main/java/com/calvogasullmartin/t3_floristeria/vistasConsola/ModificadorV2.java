package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.SeleccionadorV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV2;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC2;

public class ModificadorV2 extends SeleccionadorV2{
    
    private InOut io;
        
    private ModificadorC2 controlador;
    
    public ModificadorV2(ModificadorC2 controlador){
        io = new InOut();
        this.controlador = controlador;
    }
    
    public void interactuar(){
        try{                            
            if(seleccionarIdProducto(controlador, "modificar las unidades")){
                step2();
            }else{
                //le ha dado a cancelar al elegir un id incorrecto
            }            
        }catch (IOException ex){
            io.writeln(controlador.getErrorBD());
        }
        controlador.setModelsNull();
        new ToMenuV2(controlador).isMore("modificar las unidades de más articulos");
    }

    @Override
    protected void step2() throws IOException {
        IncrementosV2 incrV = new IncrementosV2(controlador);         
        incrV.requerirIncremento("en cuanto desea incrementar/disminuir la cantidad en stock");
        controlador.modificarCantidadEnStock();
        io.writeln("Producto añadido con éxito.");
        incrV.actualizarTotalesStock();
    }
}
