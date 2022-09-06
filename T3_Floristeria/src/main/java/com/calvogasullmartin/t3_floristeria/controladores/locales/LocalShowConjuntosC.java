package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ShowConjuntosC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class LocalShowConjuntosC extends LocalPersistenciaC implements ShowConjuntosC{    
    
    protected List<ConjuntoProductos> conjuntosLista;
    
    protected ConjuntoProductos conjuntoToShow;
    
    protected boolean isWithId;
    
    protected boolean isWithUnits;
    
    public LocalShowConjuntosC(Manager manager) {
        super(manager);                
        isWithId = false;
        isWithUnits = true;
        resetConjuntosReaded();
    }
    
    @Override
    public abstract void readConjuntos() throws IOException, SQLException, MongoException;
    
    @Override
    public final void resetConjuntosReaded(){
        conjuntosLista = new LinkedList<>();
        conjuntoToShow = new ConjuntoProductos();
    }

    @Override
    public int getNumConjuntosToShow() {
        return conjuntosLista.size();
    }
    
    @Override
    public void setConjuntoToShow(int conjuntoIndex){
        assert conjuntosLista != null;
        assert !conjuntosLista.isEmpty();
        assert conjuntoIndex >=0 && conjuntoIndex < conjuntosLista.size();
        conjuntoToShow = conjuntosLista.get(conjuntoIndex);
    }

    @Override
    public abstract String getTitle();

    @Override
    public float getValue() {
        return conjuntoToShow.getValor_Productos();
    }

    @Override
    public int getNumProductosToShow() {
        return conjuntoToShow.getProductos().size();
    }

    @Override
    public boolean isWithUnits() {
        return isWithUnits;
    }

    @Override
    public abstract String getMensageCantidad();

    @Override
    public int getCantidad(int indexProducto) {      
        assert indexProducto >= 0 && indexProducto < conjuntoToShow.getProductos().size();
        return conjuntoToShow.cantidadProducto(indexProducto);
    }
    
    @Override
    public String getId(int indexProducto) {
        assert indexProducto >= 0 && indexProducto < conjuntoToShow.getProductos().size();
        if(isWithId){
            int id = conjuntoToShow.idProducto(indexProducto);            
            return "ID "+id+")";
        }else{
            return "";
        }
    }

    @Override
    public float getPrecio(int indexProducto) {
        assert indexProducto >= 0 && indexProducto < conjuntoToShow.getProductos().size();
        return conjuntoToShow.precioProducto(indexProducto);        
    }

    @Override
    public String getDetalles(int indexProducto) {
        assert indexProducto >= 0 && indexProducto < conjuntoToShow.getProductos().size();
        return conjuntoToShow.detallesProducto(indexProducto);
    }
}
