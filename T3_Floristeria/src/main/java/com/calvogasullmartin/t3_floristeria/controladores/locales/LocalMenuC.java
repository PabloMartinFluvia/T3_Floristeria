package com.calvogasullmartin.t3_floristeria.controladores.locales;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.MenuC;
import com.calvogasullmartin.t3_floristeria.modelos.Estado;
import com.calvogasullmartin.t3_floristeria.modelos.Manager;
import com.calvogasullmartin.t3_floristeria.modelos.Menu;

public class LocalMenuC extends LocalControladorManager implements MenuC{

    private Menu menu;
    
    public LocalMenuC(Manager manager) {
        super(manager);
        menu = new Menu();
    }

    @Override
    public String getMenuMessage() {
        return menu.getMenuMessage();
    }

    @Override
    public int[] getRangeMenuOptions() {
        return menu.getRangeMenuOptions();
    }

    @Override
    public void changeState(int option) {
        assert option >= getRangeMenuOptions()[0] && option <= getRangeMenuOptions()[1];
        this.setEstate(Estado.values()[option]);
    }

    @Override
    public void accept(ControladorVisitador controlador) {
        assert controlador != null;
        controlador.visit(this);
    }
}
