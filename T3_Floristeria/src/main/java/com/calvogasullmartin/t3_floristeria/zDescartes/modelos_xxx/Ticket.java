package com.calvogasullmartin.t3_floristeria.zDescartes.modelos_xxx;

import com.calvogasullmartin.t3_floristeria.zDescartes.modelos_xxx.ProductoManager;

public class Ticket extends ProductoManager{
    // id heredado // //1:1 composicion -> embebed
    
    // Map<Producto,Integer> productos heredado //0_N:1_N AGREGACION ->
                //array de documentos (referencia al id de productos, cantidad) embebed en json / mongo
                // tabla extra en mysql registros ticket_id / producto_id / cantidad
}
