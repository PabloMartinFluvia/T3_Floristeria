package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalSeleccionadorC2;
import com.calvogasullmartin.t3_floristeria.modelos.Manager2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC2;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC2;

public class LocalModificadorC2 extends LocalSeleccionadorC2 implements ModificadorC2{

    public LocalModificadorC2(Manager2 manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC2 controlador) {
        controlador.visitar(this);
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
    public void modificarCantidadEnStock() throws IOException {
        assert getOldCantidad() >= 0;
        productoUnidadSeleccionado.setCantidad(getOldCantidad()+incremento);
        factory.getProductoUnidadDao()
                .actualizarUnidadesProductoByStockId(productoUnidadSeleccionado, getStockId());
    }
    
}
