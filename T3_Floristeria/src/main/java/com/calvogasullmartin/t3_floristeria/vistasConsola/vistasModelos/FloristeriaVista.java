package com.calvogasullmartin.t3_floristeria.vistasConsola.vistasModelos;

import com.calvogasullmartin.t3_floristeria.utils.InOut;
import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.MostrarTotalesControlador;
import java.io.IOException;
import java.util.Scanner;
import com.calvogasullmartin.t3_floristeria.controladores.InicioControlador;

public class FloristeriaVista {

    private InOut io;
    
    public FloristeriaVista() {        
        this.io = new InOut();        
    }
    
    public String pedirNombre() {
        String nombre = null;
        boolean ok = false;
        int minNumChar = 3;        
        do {
            nombre = io.readString("Por favor, introduce el nombre de la floristeria: ");            
            if (nombre.length() < minNumChar) {
                io.writeError("de al menos 3 caracteres.");
            } else {
                ok = true;
            }
        } while (!ok);
        return nombre;
    }
    
    
    
    
    
    
    
    private MostrarTotalesControlador controladorTotales;

    
    
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

    

}
