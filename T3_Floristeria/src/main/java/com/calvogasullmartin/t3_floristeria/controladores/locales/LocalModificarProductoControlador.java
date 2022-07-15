package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.ModificarProductoControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LocalModificarProductoControlador extends LocalControladorPadre implements ModificarProductoControlador{

    private boolean isUpdateUnits; //si falso -> is delete product
    
    private ConjuntoProductos stock;
    
    private ProductoUnidad productoUnidad;
    
    public LocalModificarProductoControlador(Manager estados, boolean isUpdate) {
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
        String stockToString;
        System.out.println("\n\nStock con unidades-------------------\n");
        stockToString = toStringUnits(conjunto);        
        return stockToString;
    }        
    
    private String toStringUnits(ConjuntoProductos conjunto){
        assert conjunto != null; 
        String String = conjunto.listaToStringQ();
        return String;
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
        boolean vendidoAlgunaVez = factory.getProductoUnidadesDao().isVendidoAlgunaVezByProductId(productoUnidad.getProducto().getProducto_id());
        if (vendidoAlgunaVez){
            // para indicar que ya no está a la venta, aunque la info se guarda 
            // si en algun momento se da la funcionalidad de eliminar tiquets entonces es cuando se
            // podría eliminar completamente el producto
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
