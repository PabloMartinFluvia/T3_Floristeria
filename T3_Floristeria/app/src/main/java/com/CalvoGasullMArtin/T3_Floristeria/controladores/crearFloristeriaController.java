package com.CalvoGasullMArtin.T3_Floristeria.controladores;

import com.CalvoGasullMArtin.T3_Floristeria.modelo.Floristeria;

public class crearFloristeriaController implements Contolador{
    private Floristeria floristeria;

    @Override
    public void controla() {
        this.floristeria = new Floristeria();
    }
    
}
