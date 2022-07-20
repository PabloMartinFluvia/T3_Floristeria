package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.AppCVisitorC;
import com.calvogasullmartin.t3_floristeria.controladores.ModificadorC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalSeleccionadorC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;

public class LocalModificadorC extends LocalSeleccionadorC implements ModificadorC{

    public LocalModificadorC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitorC controlador) {
        controlador.visitar(this);
    }

    @Override
    public void addIncrValor() {
        incrementoValor += incremento * productoUnidad.getProducto().getPrecio();
    }

    @Override
    public void addIncrValorStock() {
        incrementoValorStocks[getStockId()-1] += incremento * productoUnidad.getProducto().getPrecio();
    }

    @Override
    public void modificarCantidadEnStock() throws IOException {
        assert getOldCantidad() >= 0;
        productoUnidad.setCantidad(getOldCantidad()+incremento);
        factory.getProductoUnidadDao()
                .actualizarUnidadesProductoByStockId(productoUnidad, getStockId());
    }
    
}
