package com.calvogasullmartin.t3_floristeria.controladores;

import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public interface ShowFacturacionC  extends Controlador, PersistenciaC{
    
    float getFacturacion() throws IOException, SQLException, MongoException;
}
