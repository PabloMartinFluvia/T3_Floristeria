package com.calvogasullmartin.t3_floristeria.controladores.locales.Z_OLD;

import com.calvogasullmartin.t3_floristeria.controladores.Z_OLD.MostrarTotalesControlador;
import com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares.LocalPersistenciaControlador;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.controladores.AppControladorVisitor;

public class LocalMostrarTotalesControlador extends LocalPersistenciaControlador implements MostrarTotalesControlador{

    private boolean mostrarValorTienda; // si false -> muestra total Facturacion
    
    public LocalMostrarTotalesControlador(Manager estados, boolean mostrarValorTienda) {
        super(estados);
        this.mostrarValorTienda = mostrarValorTienda;
    }
    
    @Override
    public void aceptar(AppControladorVisitor controlador) {
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
