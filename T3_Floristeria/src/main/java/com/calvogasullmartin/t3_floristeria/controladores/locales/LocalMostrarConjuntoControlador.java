package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarConjuntoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LocalMostrarConjuntoControlador extends LocalControladorPadre implements MostrarConjuntoControlador{

    private boolean isStock; // si true se ocupa de stocks, si falso entonces se ocupa de tiquets.
    private boolean withUnits; // si true, devuelve los productos con sus unidades; si false se ignoran las unidades
    
    public LocalMostrarConjuntoControlador(Estados estados, boolean isStock) {
        /*
        de momento nada, aun no se necesite un atributo de tipo ConjuntoProductos
        */
        super(estados);
        this.isStock = isStock;
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    } 

    @Override
    public boolean isStock() {
        return isStock;
    }   

    @Override
    public void setWithUnits(boolean withUnits) {
        this.withUnits = withUnits;
    }        

    @Override
    public boolean isWithUnits() {
        return withUnits;
    }

    @Override
    public List<String> getAllConjuntos() throws IOException {
        List<ConjuntoProductos> listaConjuntos;
        if (isStock){
            listaConjuntos = factory.getConjuntoProductosDao().getAllStocks();
        }else{
            listaConjuntos = factory.getConjuntoProductosDao().getAllTiquets();
        }
        List<String> listaDeConjuntoToString = new LinkedList<>();
        if(listaDeConjuntoToString.size() != 0){
            for (ConjuntoProductos conjunto : listaConjuntos){
                listaDeConjuntoToString.add(conjuntoToString(conjunto));
            }
        }
        return listaDeConjuntoToString;
    }

    @Override
    public String getOneConjuntos(int conjundo_id) throws IOException {
        ConjuntoProductos conjunto = null;
        if (conjundo_id <= Categoria.values().length){
           conjunto = factory.getConjuntoProductosDao().getOneStockById(conjundo_id);
        }else {
            /*
            buscar un tiquet por id NO es requerido (por el problema
            */
        } 
        String stock;
        if(isStock){
            System.out.println("\n\nStock---------------------------------\n");
            stock = toString(conjunto);
        }
        else if(withUnits){
            System.out.println("\n\nStock con unidades-------------------\n");
            stock = toStringUnits(conjunto);
        }
        else{
            System.out.println("\n\nTicket--------------------------------\n");
            stock = toStringTicket(conjunto);
        }
        return stock;
    }

    private String conjuntoToString (ConjuntoProductos conjunto){
        assert conjunto != null; 
        String conjuntoToString = null;
        /*
        passar el stock/tiquet a String
        [teniendo en cuenta si es un tiquet o un stock]
        [en caso de ser un stock hay que tener en cuenta si se tienen que mostrar unidades o no]
        **para hacer estas distinciones usar los atributos de esta clase
        **recordar que los campos con valor null no deben mostrarse
        
        ***quizas sería mejor ir a las clases de los modelos y hacer Override al método toString 
        ***y ir implementando lo que se requiere en este metodo por partes.
        */


        return conjuntoToString;
    }

    private String toString(ConjuntoProductos conjunto){
        assert conjunto != null; 
        String String = conjunto.listaToString();
        return String;
    }
    
    private String toStringUnits(ConjuntoProductos conjunto){
        assert conjunto != null; 
        String String = conjunto.listaToStringQ();
        return String;
    }
    
    private String toStringTicket(ConjuntoProductos conjunto){
        assert conjunto != null; 
        String String = conjunto.listaToStringTicket();
        return String;
    }


    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }
    
}
