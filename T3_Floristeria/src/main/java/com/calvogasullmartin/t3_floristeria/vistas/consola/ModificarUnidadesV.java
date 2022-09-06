package com.calvogasullmartin.t3_floristeria.vistas.consola;

import com.calvogasullmartin.t3_floristeria.controladores.ModificarUnidadesC;
import com.calvogasullmartin.t3_floristeria.vistas.consola.modelos.ProductoUnidadV;
import com.mongodb.MongoException;
import java.io.IOException;
import java.sql.SQLException;

public class ModificarUnidadesV extends ElegirProductoV{
    
    private final ModificarUnidadesC controlador;
    
    public ModificarUnidadesV(ModificarUnidadesC controlador){ 
        super(controlador);
        assert controlador != null;
        this.controlador = controlador;
    }
    
    public void interact(){           
        try{                            
            update(controlador, "modificar las unidades");
            endV.askRepeatAction("modificar las unidades de más articulos");
        }catch (IOException | SQLException | MongoException ex){
            endV.manageError(ex);
        }        
    }
        
    @Override
    protected void updateProduct() throws IOException, SQLException, MongoException{
        int minReduccion = controlador.getIncrementRange()[0];
        int maxAugmento = controlador.getIncrementRange()[1];
        String mensage = "en cuanto desea incrementar/disminuir la cantidad en stock";
        int incremento = new ProductoUnidadV().askIncrement(mensage, minReduccion, maxAugmento);      
        controlador.updateUnitsStock(incremento);
        controlador.updateTotalsValues();
        io.writeln("Unidades del producto modificadas con éxito");
        controlador.resetProductUpdated();
    }
}
