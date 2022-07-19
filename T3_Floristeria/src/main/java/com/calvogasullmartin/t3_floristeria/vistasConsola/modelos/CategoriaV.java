package com.calvogasullmartin.t3_floristeria.vistasConsola.modelos;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.utils.PedirEnteroLimitado;

/*
Aunque sea una vista, no hay problema en que esté acoplada al modelo,
ya que es un Enum.
*/
public class CategoriaV {
    
    private InOut io = new InOut();
    
    private boolean allOptionsPosible;
    
    private String mensageOpcion;
    
    private PedirEnteroLimitado requerimiento;
    
    private int maxOpciones;
    
    private int minOpciones;

    public CategoriaV(boolean allOptionsPosible, String mensageOpcion) {
        this.allOptionsPosible = allOptionsPosible;
        this.mensageOpcion = mensageOpcion;
        this.maxOpciones = Categoria.values().length;;
    }
    
    //devuelve indice en el enum (i <0 si se selecciona opción todos)
    public int pedirIndexCategoria(){        
         mostrar();                
        return requerirOpcion()-1;
    }
    
    private void mostrar(){
        io.writeln("\nOpciones: ");
        for (int i = 0; i<maxOpciones; i++){
            String categoria = "\t"+(i+1)+") "+Categoria.values()[i];
            io.writeln(categoria);
        } 
    }
    
    private int requerirOpcion(){
        if(allOptionsPosible){
           minOpciones = ofrecerAll();
        }else{
            minOpciones = 1;
        }       
        requerimiento = new PedirEnteroLimitado("Seleccione "+mensageOpcion ,minOpciones, maxOpciones);        
        return requerimiento.read();
    }
    
    private int ofrecerAll() {        
        io.writeln("\n\t0) TOD@S");
        return 0;
    }
}
