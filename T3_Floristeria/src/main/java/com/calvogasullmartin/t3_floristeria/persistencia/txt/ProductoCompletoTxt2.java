package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos2;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao2;

public class ProductoCompletoTxt2 extends GenericDaoTxt2<ProductoCompleto2, Integer> implements ProductoCompletoDao2{

    private String atributo_stocks = Floristeria2.class.getDeclaredFields()[4].getName();     
    
    private String atributo_productosUnidad = ConjuntoProductos2.class.getDeclaredFields()[2].getName(); 
    
    private String atributo_producto = ProductoUnidad2.class.getDeclaredFields()[0].getName(); 
    
    private String atributo_id = ProductoCompleto2.class.getDeclaredFields()[0].getName();
    
    private String atributo_nombre = ProductoCompleto2.class.getDeclaredFields()[2].getName();
    
    private String atributo_precio = ProductoCompleto2.class.getDeclaredFields()[3].getName();
    
    private String atributo_altura = ProductoCompleto2.class.getDeclaredFields()[4].getName();
    
    private String atributo_color = ProductoCompleto2.class.getDeclaredFields()[5].getName();
    
    private String atributo_material = ProductoCompleto2.class.getDeclaredFields()[6].getName();
    
    @Override
    public int getProductoId(ProductoCompleto2 producto) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);       
        gestor.setListNodes_findAllFieldsByName(atributo_producto);//productosCompletos  lista (en todos los stocks)   
        //no se mira ni el stock ni la categoria -> ya lo filtraran otros
        gestor.replaceList_NodesListWithChildStringValue(atributo_nombre, producto.getNombre());
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

    @Override
    @SuppressWarnings("unchecked")
    public ProductoCompleto2 findProductoByIdInStockId(int producto_id, int stock_id) throws IOException {        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);        
        gestor.replaceNode_isArray_nodeByIndex(stock_id-1); //stock
        gestor.replaceNode_findFieldByName(atributo_productosUnidad);//productos
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_id, producto_id);//stock que tiene el producto        
        if (gestor.isNodeNull()){//no está en la lista de productos ese stock
            return null;
        }else{                        
            gestor.replaceNode_findFieldByName(atributo_producto);
            return (ProductoCompleto2) gestor.parseNodeToObject(ProductoCompleto2.class); 
        }        
    }
    
    
}
