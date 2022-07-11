package com.calvogasullmartin.t3_floristeria.modelos;

public enum Estado { 
   //Situaciones en que el usuario tiene que tomar una decisión relevante para el
    //se llegará a otro estado en que vea información relevante y, tenga que tomar una nueva decisión.
    //* Des de un estado a otro pueden haber N casos de uso 
    //* Incluso un caso de uso puede devolverte al mismo estado si sucede algun "if"
    //** initial y exit se incluyen siempre
    INITIAL,    
    EN_MENU,
    NUEVO_PRODUCTO,
    MOSTRAR_STOCK,
    MOSTRAR_TIQUETS,
    //aqui habran más
    EXIT;    
}
