package com.calvogasullmartin.t3_floristeria.vistasConsola;

import com.calvogasullmartin.t3_floristeria.controladores.AddProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.controladores.NuevaVentaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.utils.InOut;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConjuntoProductosVista {

    private InOut io = new InOut();
    private Categoria categoria; // enum con todos los tipos de productos / stocks posibles
    //al ser un enum NO es un pecado que esta vista conozca información del modelo

    private MostrarConjuntoControlador mostrarControlador;
    private ModificarProductoControlador modificarControlador;
    private NuevaVentaControlador nuevaVentaControlador;

    public ConjuntoProductosVista() {
    }

    
    
    
    
    

    
    
    private Integer saberStockId(boolean isAllPosible) {        
        mostrarOpciones(isAllPosible);        
        return pedirOpcion(isAllPosible);//si conjunto_id = null -> todos
    }
    
    private void mostrarOpciones(boolean isAllPosible){
        String mensaje = "Categorias posibles: \n"
        + "\t1) Arboles.\n"
        + "\t2) Flores.\n"
        + "\t3) Decoración.";        
        if(isAllPosible){
            mensaje += "\t0) Todos.";
        }
        System.out.println(mensaje);
    }
    
    private Integer pedirOpcion(boolean isAllPosible){
        int min;
        if(isAllPosible){
            min = 0;
        }else{
            min = 1;
        }
        Integer conjunto_id = getInt("Seleccione categoria: ",min,3);   
        if(conjunto_id.equals(0)){
            return null;
        }
        return conjunto_id;
    }
    
    private int getInt(String mensaje, int min, int max) {
        Scanner sc = new Scanner(System.in);        
        int input;
        boolean ok = false;
        do{            
            //io.writeln(mensaje);
            //System.out.print(mensaje);
            //input = sc.nextInt();
            io.writeln(mensaje);
            input = io.readInt("");
            if (input>=min && input<=max){
                ok = true;
            }else{
                io.writeln("Error: opcion debe estar entre "+min+" y "+max);
            }
                
        }while(!ok);
        return input;
    }
    
    private boolean isCorrecto(String mensaje) {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int input;
        do{
            System.out.println(mensaje);
            input = sc.nextInt();
            if (input>=0 && input<=1){
                ok = true;
            }
        }while(!ok);
        
        ok = true;
        if(input==0){
            ok=false;
        }
        return ok;
    }
    
    public void interactuar(MostrarConjuntoControlador mostrarControlador) {
        this.mostrarControlador = mostrarControlador;
        mostrarControlador.setWithUnits(true); // por defecto se muestra el conjuno(s) con las unidades de los productos        
        try {
            if (!mostrarControlador.isStock()) {                
                mostrarTodo();
            } else {
                preguntarSiIncluirUnidades();
                Integer conjunto_id = saberStockId(true); // puede ser null si quiere ver todos los stocks
                mostrarConjuntoProductosSegunEleccion(conjunto_id);
            }
        } catch (IOException ex) {
            /*
            mostrar un mensaje de error diciendo que no se ha podido acceder a la BBDD
             */
        }
        mostrarControlador.seleccionarMenu();
    }

    public void interactuar(ModificarProductoControlador modificarControlador) {        
        this.modificarControlador = modificarControlador;
        Integer conjunto_id = saberStockId(false);        
        try {
            mostrarUnConjuntoV2(conjunto_id);
            new ProductoUnidadVista().interactuar(modificarControlador);
        } catch (IOException ex) {
            System.out.println("Base de datos inaccesible.");
            modificarControlador.seleccionarMenu();
        }
    }

    public void interactuar(NuevaVentaControlador nuevaVentaControlador) {
        this.nuevaVentaControlador = nuevaVentaControlador;
        boolean addMoreProducts = true;
        nuevaVentaControlador.inicializarTiquet();
        try {
            do {
                printStocks();
                int producto_id = 0;//para que ahora no de error            
                /*
            pedir el id del producto a añadir al tiquet y guardarlo en la variable
                 */
                if (isValidProductId(producto_id)) {
                    int cantidadEnLaVenta = 0;//para que ahora no de error            
                    /*
                pedir unidades vendidas de ese producto y guardarlo en la variable                
                     */
                    if (isVentaPosible(cantidadEnLaVenta)) {
                        nuevaVentaControlador.addToTiquet();
                    } else {
                        /*
                    decirle que no ho hay tantas unidades en stock
                         */
                    }
                } else {
                    /*
                decirle que no hay ningun producto con ese id
                     */
                }
                if (isVentaFinalizada()) {
                    addMoreProducts = false;
                }
            } while (addMoreProducts);
            nuevaVentaControlador.guardarTiquet();
            nuevaVentaControlador.updateStockValues();
        } catch (IOException ex) {
            /*
            mostrar mensaje de que no se ha podido conectar a la BBDD
             */
        }
        nuevaVentaControlador.seleccionarMenu();
    }

    private void printStocks() throws IOException {
        List<String> mensageStocks = nuevaVentaControlador.getAllStocks();
        /*
        mostrar la lista de stocks (cada elemento tiene el formato, en string, de un ConjuntoProducto
         */
    }

    private boolean isValidProductId(int producto_id) throws IOException {
        return nuevaVentaControlador.isProductUnidadInSomeStock(producto_id);
    }

    private boolean isVentaPosible(int cantidad) {
        return nuevaVentaControlador.isSuficienteStockParaLaVenta(cantidad);
    }

    private boolean isVentaFinalizada() {
        boolean end = isCorrecto("Quiere añadir otro artículo?\n"
        + "\t1) Si.\n"
        + "\t0) No.\n");
        return end;
    }

    private void mostrarStock(boolean isAllPossible) throws IOException {
        Integer conjunto_id = saberStockId(isAllPossible); // puede ser null si quiere ver todos los stocks
        mostrarConjuntoProductosSegunEleccion(conjunto_id);
    }

    private void mostrarConjuntoProductosSegunEleccion(Integer stock_id) throws IOException {
        if (stock_id == null) {
            mostrarTodo();
        } else {
            mostrarUnConjunto(stock_id);
        }
    }
    
    private void mostrarUnConjunto(Integer conjunto_id)throws IOException{
        try {            
            String conjuntoProductosUnidad = mostrarControlador.getOneConjuntos(conjunto_id);
            System.out.println(conjuntoProductosUnidad);
        } catch (IOException ex) {
            System.out.println("Base de datos inaccesible.");
        }
    }
    
    
    
    

    //chapuza para que funcione con otro controlador el controlador mostrar y el controlador modificar tienen cosas comunes
    private void mostrarUnConjuntoV2(Integer conjunto_id) throws IOException{                
        try {            
            String conjuntoProductosUnidad = modificarControlador.getOneConjuntos(conjunto_id);
            System.out.println(conjuntoProductosUnidad);
        } catch (IOException ex) {
            System.out.println("Base de datos inaccesible.");
        }
    }

    private void mostrarTodo() throws IOException {

        List<String> tiquets_stocks = mostrarControlador.getAllConjuntos();
        /*
            mostrar la lista de listas de productos (en formato string)
            ** prguntandole al controlador.isWithUnits i .isStock se puede saber si son todos
            los tiquets o todos los stocks, i si incluyen unidades (los tiquets siempre la incluyen)
            ** son tiquets: cada List<String> es un tiquet -> List<List<String>> es una lista de todos los tiquets
            ** son stocks: cada List<String> es un stock -> List<List<String>> es una lista de todos los stocks {tamaño 3}
            */
            if(tiquets_stocks.size()!=0){
                for (String string : tiquets_stocks) {
                    System.out.println(string);
                }
            }
            else{
                if(mostrarControlador.isStock()){
                    System.out.println("No hay stocks.");
                }else {
                    System.out.println("No hay tickets.");
                }
                
            }
    }
    
    private void preguntarSiIncluirUnidades (){
        boolean incluirUnidades = isCorrecto("Quiere incluir las unidades?\n"
        + "\t1) Si.\n"
        + "\t0) No.\n");
        mostrarControlador.setWithUnits(incluirUnidades);
    }
    
    
}
