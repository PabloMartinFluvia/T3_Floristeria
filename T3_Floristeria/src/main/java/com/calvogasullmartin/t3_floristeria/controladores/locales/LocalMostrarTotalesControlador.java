package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorPadreVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Estados;
import java.io.IOException;

public class LocalMostrarTotalesControlador extends LocalControladorPadre implements MostrarTotalesControlador{

    private boolean mostrarValorTienda; // si false -> muestra total Facturacion
    
    public LocalMostrarTotalesControlador(Estados estados, boolean mostrarValorTienda) {
        super(estados);
        this.mostrarValorTienda = mostrarValorTienda;
    }
    
    @Override
    public void aceptar(ControladorPadreVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public boolean isMostrarValorTienda() {
        return mostrarValorTienda;
    }

    @Override
    public void seleccionarMenu() {
        this.setEstado(Estado.EN_MENU);
    }

    @Override
    public float getTotalStocks() throws IOException {
        return factory.getFloristeriaDao().getValorFloristeria();
    }

    @Override
    public float getTotalTiquets() throws IOException {
        return factory.getFloristeriaDao().getFacturacionFloristeria();
    }
    
}
