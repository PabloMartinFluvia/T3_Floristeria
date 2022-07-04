package com.calvogasullmartin.t3_floristeria.modelos;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoManager;

public class Stock extends ProductoManager{
    // id heredado // //1:1 composicion -> embebed
    
    // Map<Producto,Integer> productos heredado //1:0_N composición ->
                            //array de documentos (Producto concreto, cantidad) embebed en json / mongo
                            // tabla extra en mysql registros stock_id / producto_id / cantidad
}
