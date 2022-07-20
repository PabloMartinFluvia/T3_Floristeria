package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.SeleccionadorV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.ToMenuV;
import com.calvogasullmartin.t3_floristeria.vistasConsola.auxiliares.IncrementosV;
import java.io.IOException;

public class ModificadorV extends SeleccionadorV{
    
    private InOut io;
        
    private ModificadorC controlador;
    
    public ModificadorV(ModificadorC controlador){
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
        new ToMenuV(controlador).isMore("modificar las unidades de más articulos");
    }

    @Override
    protected void step2() throws IOException {
        IncrementosV incrV = new IncrementosV(controlador); 
        incrV.requerirIncremento("en cuanto desea incrementar/disminuir la cantidad en stock");
        controlador.modificarCantidadEnStock();
        io.writeln("Producto añadido con éxito.");
        incrV.actualizarTotalesStock();
    }
}
