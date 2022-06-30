package com.calvogasullmartin.t3_floristeria.persistencia.dto;

import com.calvogasullmartin.t3_floristeria.modelos.ProductoManager;

public class TicketDto extends ProductoManager{
    // id heredado // //1:1 composicion -> embebed
    
    // Map<Producto,Integer> productos heredado //0_N:1_N AGREGACION ->
                //array de documentos (referencia al id de productos, cantidad) embebed en json / mongo
                // tabla extra en mysql registros ticket_id / producto_id / cantidad
}
