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
        return conjuntoToString(conjunto);
    }
        
    private String conjuntoToString (ConjuntoProductos conjunto){
        assert conjunto != null; 
        String stockToString;
        if(isStock){
            if(withUnits){
                System.out.println("\n\nStock con unidades-------------------\n");
                stockToString = toStringUnits(conjunto);
            }
            else {
                System.out.println("\n\nStock---------------------------------\n");
                stockToString = toString(conjunto);
            }
        }
        else{
            System.out.println("\n\nTicket--------------------------------\n");
            stockToString = toStringTicket(conjunto);
        }
        return stockToString;
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
