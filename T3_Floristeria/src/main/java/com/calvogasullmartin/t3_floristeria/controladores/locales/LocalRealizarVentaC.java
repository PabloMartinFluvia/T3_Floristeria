package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.RealizarVentaC;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.mongodb.MongoException;
import java.sql.SQLException;

public class LocalRealizarVentaC extends LocalElegirProductoC implements RealizarVentaC {

    private ConjuntoProductos ticket;

    public LocalRealizarVentaC(Manager manager) {
        super(manager);
        resetTicket();
    }

    @Override
    public void updateUnitsStock(int incremento) throws IOException, SQLException, MongoException {
        assert incremento < 0;
        errorBD = "Error! No se ha podido actualizar la cantidad en la BD.";
        int productoId = productoUnidadUpdating.getProducto().getProducto_id();        
        DaoFactory.getFactory().getProductoUnidadDao()
                .incrementarCantidadByStockIdProductoId(stock_id_updating, productoId, incremento);
        float canvioValor = incremento * productoUnidadUpdating.getProducto().getPrecio();
        variacionValorStocks[stock_id_updating - 1] += canvioValor;
        addProductInTicket(incremento, canvioValor);
    }
    
    private void addProductInTicket(int incremento, float canvioValor){
        ProductoUnidad productoVendido = new ProductoUnidad();
        productoVendido.setProducto(productoUnidadUpdating.getProducto());
        productoVendido.setCantidad(-incremento);
        manageProductDuplicated(productoVendido);
        float valorTicket = ticket.getValor_Productos() + (-canvioValor);
        ticket.setValor_Productos(valorTicket);
    }
    
    private void manageProductDuplicated(ProductoUnidad productoVendido){
        int idProductoVendido = productoVendido.idProducto();
        boolean duplicated = false;
        for(ProductoUnidad product: ticket.getProductos()){
            if(product.idProducto() == idProductoVendido){
                duplicated = true;
                int unitsUpdated = product.getCantidad() + productoVendido.getCantidad();
                product.setCantidad(unitsUpdated);
                break;
            }
        }
        if(!duplicated){
            ticket.getProductos().add(productoVendido);
        }        
    }

    @Override
    public boolean isTicketValid() {
        return ticket != null && !ticket.getProductos().isEmpty();
    }

    @Override
    public void createTicket() throws IOException, SQLException, MongoException {
        errorBD = "Error! No se ha podido guardar el ticket en la BD";
        DaoFactory.getFactory().getConjuntoProductosDao().createTiquet(ticket);

    }
    
    @Override
    public void updateTotalsValues() throws IOException, SQLException, MongoException {
        float variacionTotal = 0f;
        for (int i = 0; i < variacionValorStocks.length; i++){    
            errorBD = "Error! No se ha podido actuzlizar el valor del stock de "+Categoria.values()[i];
            factory.getConjuntoProductosDao().incrementarValorEnStockById(i+1, variacionValorStocks[i]);
            variacionTotal += variacionValorStocks[i];
        }
        errorBD = "Error! No se ha podido actualizar el valor total de la floristería";
        factory.getFloristeriaDao().incrementarValorFloristeria(variacionTotal);
        errorBD = "Error! No se ha podido actualizar la facturación total de la floristería";
        factory.getFloristeriaDao().incrementarFacturacionFloristeria(-variacionTotal);
    }

    @Override
    public int getMinVenta() {
        return getCANTIDAD_VENTA_MINIMA();
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        controlador.visit(this);
    }

    @Override
    public final void resetTicket() {
        ticket = new ConjuntoProductos();
    }
}
