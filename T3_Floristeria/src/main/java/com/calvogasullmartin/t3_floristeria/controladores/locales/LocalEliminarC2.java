package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.EliminarC2;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalSeleccionadorC2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.io.IOException;

public class LocalEliminarC2 extends LocalSeleccionadorC2 implements EliminarC2{

    public LocalEliminarC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC2 controlador) {
        controlador.visistar(this);
    }

    @Override
    public void modificarCantidadEnStock() throws IOException {
        assert getOldCantidad() >= 0;
        productoUnidadSeleccionado.setCantidad(getOldCantidad()+incremento);
        factory.getProductoUnidadDao()
                .actualizarUnidadesProductoByStockId(productoUnidadSeleccionado, getStockId());
    }
    
    @Override
    public void addIncrValor() {
        incrementoValor += incremento * productoUnidadSeleccionado.getProducto().getPrecio();
    }

    @Override
    public void addIncrValorStock() {
        incrementoValorStocks[getStockId()-1] += incremento * productoUnidadSeleccionado.getProducto().getPrecio();
    }

    

    @Override
    public void eliminarProducto() throws IOException {
        if(!isVendidoAlgunaVez()){
            eliminacionTotal();
        }else{
            eliminarRelacionConStock();
        }
    }
    
    private void eliminacionTotal(){
        factory.getProductoUnidadDao().deleteInStocksById(getStockId(),productoUnidadSeleccionado);
    }
    
}
