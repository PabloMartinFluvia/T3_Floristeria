package com.calvogasullmartin.t3_floristeria.controladores.locales.auxiliares;

import com.calvogasullmartin.t3_floristeria.controladores.AppCVisitor;
import com.calvogasullmartin.t3_floristeria.controladores.auxiliares.IncrementadorC;
import com.calvogasullmartin.t3_floristeria.controladores.locales.LocalPersistenciaC;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import java.io.IOException;

public abstract class LocalIncrementadorC extends LocalPersistenciaC implements IncrementadorC{

    protected int incremento;
    
    protected float incrementoValor;
    
    public LocalIncrementadorC(Manager manager) {
        super(manager);
    }
    
    @Override
    public abstract void aceptar(AppCVisitor controlador);

    @Override
    public void resetIncr() {
        incremento = 0;
    }
    
    @Override
    public void resetIncrValor(){
        incrementoValor = 0f;
    }

    @Override
    public int getMaxIncr() {
        return this.getMAX_UNIDADES_EN_STOCK() - this.getOldCantidad();
    }

    @Override
    public int getMinIncr() {
        return -this.getOldCantidad();
    }
    
    protected abstract int getOldCantidad();

    @Override
    public void setIncr(int incremento) {
        this.incremento = incremento;
    }

    @Override
    public abstract void addIncrValor();
    
    @Override
    public void updateValorTotal() throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateValorStock() throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
