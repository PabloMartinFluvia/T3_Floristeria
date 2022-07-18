package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.ValorC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;

public class LocalValorC extends LocalPersistenciaC implements ValorC{

    public LocalValorC(Manager manager) {
        super(manager);
    }

    @Override
    public void aceptar(AppCVisitor controlador) {
        controlador.visitar(this);
    }

    @Override
    public float getValor() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
