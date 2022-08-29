package com.calvogasullmartin.t3_floristeria.vistas;

import com.calvogasullmartin.t3_floristeria.controladores.ControladorVisitador;
import com.calvogasullmartin.t3_floristeria.controladores.Controlador;

/*
hereda de ControladorVisitador para poder aplicar la técnica de doble despacho.
Y así poder distinguir "qué hacer" segun el tipo de controlador, sin preguntar
directamente que tipo contreto es.
*/
public interface Vista extends ControladorVisitador{
        
    public void interact(Controlador controlador);
}
