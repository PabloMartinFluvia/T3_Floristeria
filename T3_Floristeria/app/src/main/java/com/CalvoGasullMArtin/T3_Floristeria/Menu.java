package com.CalvoGasullMArtin.T3_Floristeria;

import com.CalvoGasullMArtin.T3_Floristeria.utils.InOut;

public class Menu {
    
    private Integer opcion;
    private InOut io;

    public Menu() {
        this.opcion = null;
        this.io = new InOut();
    }
    
    public void mostrarMenu(){
        String mensaje = "MENÚ INICAL:\n"
                + "\t1) Crear floristería.\n"
                + "\t2) Añadir artículo.\n"
                + "\t3) Eliminar artículo.\n"
                + "\t4) Mostrar lista del stock.\n"
                + "\t5) Mostrar stock con unidades.\n"
                + "\t6) Mostrar valor total del stock.\n"
                + "\t7) Crear ticket.\n"
                + "\t8) Mostrar tickets.\n"
                + "\t9) Mostrar total ventas.\n\n"
                + "\t0) SALIR.\n";        
        io.writeln(mensaje); 
    }
    
    public int seleccionaOpcion(){      
        boolean ok =false;
        do {
            opcion = io.readInt("Selecciona Opcion: ");
            if(opcion>=0 && opcion<=9){
                ok = true;
            }else{
                io.writeln("Opción no correcta. Debe ser entre 0 i 10");
            }            
        }while(!ok);
        return opcion;
    }
}
