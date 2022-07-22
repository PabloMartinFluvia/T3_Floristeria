package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad2;
import java.util.LinkedList;
import java.util.List;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.ConjuntosC2;

public abstract class LocalConjuntosC2 extends LocalPersistenciaC2 implements ConjuntosC2{

    protected List<ConjuntoProductos2> conjuntos;
    
    protected boolean withUnits;
    
    protected boolean withId;
    
    public LocalConjuntosC2(Manager2 manager) {
        super(manager);        
    }

    
    @Override
    public abstract void aceptar(AppCVisitorC2 controlador);

    @Override
    public void setConUnidades(boolean withUnits) {
        this.withUnits = withUnits;
    }

    @Override
    public void setConId(boolean withId) {
        this.withId = withId;
    }
    
    @Override
    public void resetModels() {
        conjuntos = new LinkedList<>();
    }
    
    @Override
    public int getNumConjuntosToShow() {
        return conjuntos.size();
    }

    @Override
    public abstract String getTitle(int indexConjunto);

    @Override
    public float getValue(int indexConjunto) {
        assert indexConjunto < conjuntos.size();
        return conjuntos.get(indexConjunto).getValor_Productos();
    }

    @Override
    public int getNumProductosToShow(int indexConjunto) {
        assert indexConjunto < conjuntos.size();
        ConjuntoProductos2 conjunto = conjuntos.get(indexConjunto);
        return conjunto.getProductos().size();
    }

    @Override
    public int getCantidad(int[] indexs) {
        assert indexs[0] < conjuntos.size();
        assert indexs[1] < conjuntos.get(indexs[0]).getProductos().size();
        ProductoUnidad2 productoUnidad = conjuntos.get(indexs[0]).getProductos().get(indexs[1]);
        return productoUnidad.getCantidad();
    }

    @Override
    public boolean isConUnidades() {
        return withUnits;
    }

    @Override
    public abstract String getMensageCantidad();

    @Override
    public String getId(int[] indexs) {
        assert indexs[0] < conjuntos.size();
        assert indexs[1] < conjuntos.get(indexs[0]).getProductos().size();
        ProductoUnidad2 productoUnidad = conjuntos.get(indexs[0]).getProductos().get(indexs[1]);
        if(withId){
            return "Id "+productoUnidad.getProducto().getProducto_id()+") ";
        }else{
            return "";
        }
    }

    @Override
    public float getPrecio(int[] indexs) {
        assert indexs[0] < conjuntos.size();
        assert indexs[1] < conjuntos.get(indexs[0]).getProductos().size();
        ProductoUnidad2 productoUnidad = conjuntos.get(indexs[0]).getProductos().get(indexs[1]);
        return productoUnidad.getProducto().getPrecio();
    }

    @Override
    public String getDetalles(int[] indexs) {
        assert indexs[0] < conjuntos.size();
        assert indexs[1] < conjuntos.get(indexs[0]).getProductos().size();
        ProductoUnidad2 productoUnidad = conjuntos.get(indexs[0]).getProductos().get(indexs[1]);
        return productoUnidad.getProducto().detallesProducto();
    }
    
    @Override
    public void setModelsNull() {
        conjuntos = null;
    }    
}
