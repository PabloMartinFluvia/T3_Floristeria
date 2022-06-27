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
        String mensaje = "MEN� INICAL:\n"
                + "\t1) Crear florister�a.\n"
                + "\t2) A�adir art�culo.\n"
                + "\t3) Eliminar art�culo.\n"
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
                io.writeln("Opci�n no correcta. Debe ser entre 0 i 9");
            }            
        }while(!ok);
        return opcion;
    }
}
