package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LocalModificarProductoControlador extends LocalControladorPadre implements ModificarProductoControlador{

    private boolean isUpdateUnits; //si falso -> is delete product
    
    private ConjuntoProductos stock;
    
    private ProductoUnidad productoUnidad;
    
    public LocalModificarProductoControlador(Estados estados, boolean isUpdate) {
        super(estados);
        this.isUpdateUnits = this.isUpdateUnits;
    }
        
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public boolean IsUpdateUnits() {
        return isUpdateUnits;
    }

    //COPY PASTE DE mostrar controlador
    @Override
    public String getOneConjuntos(int conjundo_id) throws IOException {  
        assert conjundo_id <= Categoria.values().length;
        this.stock = factory.getConjuntoProductosDao().getOneStockById(conjundo_id);                     
        return conjuntoToString(stock);
    }
    
    //casi COPY PASTE DE mostrar controlador
    private String conjuntoToString (ConjuntoProductos conjunto){
        assert conjunto != null; 
        String conjuntoToString = null;
        /*
        passar el stock a String
        mostrar unidades
        **para hacer estas distinciones usar los atributos de esta clase
        **recordar que los campos con valor null no deben mostrarse
        
        ***quizas sería mejor ir a las clases de los modelos y hacer Override al método toString 
        ***y ir implementando lo que se requiere en este metodo por partes.
        */
        return conjuntoToString;
    }
    
    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public boolean isProductoIdValid(int producto_id) {
        boolean ok = false; // solo para que no de error
        List<ProductoUnidad> listaProductosUnidad = stock.getProductos();
        Iterator<ProductoUnidad> iterador = listaProductosUnidad.iterator();
        while(!ok && iterador.hasNext()){
            ProductoUnidad productoUnidad = iterador.next();
            if(productoUnidad.getProducto().getProducto_id() == producto_id){
                ok = true;
                this.productoUnidad = productoUnidad;
            }
        }
        return ok;
    }
    
    //copy paste de add produco controlador, pero ahora resta, ya que se llama cuando se elimina
    @Override
    public void disminuirValoresStock() throws IOException {
        float incrementoValor = - productoUnidad.getProducto().getPrecio() * productoUnidad.getCantidad();
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);        
        factory.getConjuntoProductosDao().incrementarValorEnStockById(stock.getId(), incrementoValor);
    }

    @Override
    public void eliminarProductoUnidad() throws IOException {
        int nVecesEnTiquet = factory.getProductoUnidadesDao().enCuantosTiquetsEsta(productoUnidad.getProducto().getProducto_id());
        if (nVecesEnTiquet > 0){
            productoUnidad.setCantidad(-1);
            factory.getProductoUnidadesDao().actualizarUnidadesProductoByStockId(productoUnidad, stock.getId());
        }{
            factory.getProductoUnidadesDao().deleteInStock(productoUnidad, stock.getId());
        }    
    }

    @Override
    public int getUnidadesActuales() {
        return productoUnidad.getCantidad();
    }

    @Override
    public void incrementarProductoUnidad(int incremento) throws IOException {
        this.productoUnidad.setCantidad(getUnidadesActuales()+incremento);
        factory.getProductoUnidadesDao().actualizarUnidadesProductoByStockId(productoUnidad, stock.getId());
    }

    @Override
    public void actualizarValoresStock(int incremento) throws IOException {
        float incrementoValor =  productoUnidad.getProducto().getPrecio() * incremento;
        factory.getFloristeriaDao().incrementarValorFloristeria(incrementoValor);        
        factory.getConjuntoProductosDao().incrementarValorEnStockById(stock.getId(), incrementoValor);
    }
}
