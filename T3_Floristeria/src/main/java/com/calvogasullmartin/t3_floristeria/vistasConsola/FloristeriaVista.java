package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.ArrancarAppControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import java.io.IOException;
import java.util.Scanner;

public class FloristeriaVista {

    private InOut io;
    
    private MostrarTotalesControlador controladorTotales;

    public FloristeriaVista() {        
        this.io = new InOut();        
    }

    public void interactuar(ArrancarAppControlador controlador) {
        assert controlador != null;
        String nombre = pedirNombre();        
        try {
            controlador.iniciarFloristeria(nombre);
            controlador.guardarUnicaFloristeria();
            //io.write("Floristeria guardada con éxito.");
            System.out.println("Floristeria guardada con éxito.");
        } catch (IOException ex) {
            System.out.println("Base de datos inaccesible.");
        }
    }
    
    public void interactuar(MostrarTotalesControlador controlador){
        assert controlador != null;
        this.controladorTotales = controlador;
        try {
            if (controladorTotales.isMostrarValorTienda()){
                mostrarTotalStocks();            
            }else {
                mostrarTotalVentas();            
            }
        } catch (IOException ex){
            System.out.println("Base de datos inaccesible.");
        }
        controladorTotales.seleccionarMenu();
    }
    
    private void mostrarTotalStocks () throws IOException{
        float total = controladorTotales.getTotalStocks();
        imprimirTotal("todos los stocks", total);
    }
    
    private void mostrarTotalVentas() throws IOException{
        float total = controladorTotales.getTotalTiquets();
        imprimirTotal("todas las ventas", total);
    }
    
    private void imprimirTotal(String title, float total){
        String mensage = "El importe total de "+title+" es de: "+total;
        //io.writeln(mensage);
        System.out.println(mensage);
    }

    private String pedirNombre() {
        String nombre = null;
        boolean ok = false;
        int minNumChar = 3;        
        do {
            //nombre = io.readString("Introduce el nombre de la floristeria: ");
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduce el nombre de la floristeria: ");
            nombre = entrada.nextLine();
            if (nombre.length() < minNumChar) {
                //io.writeError("de al menos 3 caracteres");
                System.out.println("Error: el nombre debe tener al menos 3 caracteres.");
            } else {
                ok = true;
            }
        } while (!ok);
        return nombre;
    }

}
