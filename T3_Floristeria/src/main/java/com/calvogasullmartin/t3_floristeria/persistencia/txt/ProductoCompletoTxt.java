package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import java.io.IOException;

public class ProductoCompletoTxt extends GenericDaoTxt<ProductoCompleto, Integer> implements ProductoCompletoDao{

    private String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();     
    
    private String atributo_producto = ProductoUnidad.class.getDeclaredFields()[0].getName(); 
    
    private String atributo_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
    
    private String atributo_precio = ProductoCompleto.class.getDeclaredFields()[2].getName();
    
    private String atributo_altura = ProductoCompleto.class.getDeclaredFields()[3].getName();
    
    private String atributo_color = ProductoCompleto.class.getDeclaredFields()[4].getName();
    
    private String atributo_material = ProductoCompleto.class.getDeclaredFields()[5].getName();
    
    @Override
    public int getProductoId(ProductoCompleto producto) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);       
        gestor.setListNodes_findAllFieldsByName(atributo_producto);//productosCompletos  lista (en todos los stocks)   
        //no se mira ni el stock ni la categoria -> ya lo filtraran otros
        gestor.replaceList_NodesListWithChildFloatValue(atributo_precio, producto.getPrecio());
        if(producto.getAltura()!=null){        
            gestor.replaceList_NodesListWithChildStringValue(atributo_altura, producto.getAltura().name());
        }
        if(producto.getColor()!=null){
            gestor.replaceList_NodesListWithChildStringValue(atributo_color, producto.getColor());   
        }
        if(producto.getMaterial()!=null){
            gestor.replaceList_NodesListWithChildStringValue(atributo_material, producto.getMaterial().name());
        }
        
              
        if(gestor.isListNodesEmpty()){
            return 0;
        }else{
            gestor.replaceNode_fromUniqueElementInListNode();
            gestor.replaceNode_findFieldByName(atributo_id);
            return gestor.getIntValue_fromNode();
        }    
    }
    
}
