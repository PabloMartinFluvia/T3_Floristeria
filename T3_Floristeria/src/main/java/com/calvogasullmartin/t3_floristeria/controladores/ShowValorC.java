package com.calvogasullmartin.t3_floristeria.controladores;

import java.io.IOException;
import java.sql.SQLException;

public interface ShowValorC extends Controlador, PersistenciaC{
    
    float getValor() throws IOException, SQLException;
}
