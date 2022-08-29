package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import java.io.IOException;

public class ProductoCompletoTxt extends GenericDaoTxt<ProductoCompleto, Integer> implements ProductoCompletoDao{

    private String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();     
    
    private String atributo_productosUnidad = ConjuntoProductos.class.getDeclaredFields()[2].getName();         
    
    private String atributo_producto = ProductoUnidad.class.getDeclaredFields()[0].getName(); 
    
    private String atributo_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
    
    private String atributo_nombre = ProductoCompleto.class.getDeclaredFields()[2].getName();
    
    private String atributo_precio = ProductoCompleto.class.getDeclaredFields()[3].getName();
    
    private String atributo_altura = ProductoCompleto.class.getDeclaredFields()[4].getName();
    
    private String atributo_color = ProductoCompleto.class.getDeclaredFields()[5].getName();
    
    private String atributo_material = ProductoCompleto.class.getDeclaredFields()[6].getName();
    
    @Override
    public int findId(ProductoCompleto producto) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_fromMain();
        //lista de todos los productos, tanto en stock como en tickets        
        gestor.setListNodes_findAllFieldsByName(atributo_producto);
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
            gestor.replaceNode_fromFirstElementInListNode();
            gestor.replaceNode_findFieldByName(atributo_id);
            return gestor.getIntValue_fromNode();
        }    
    }
        
    @Override
    @SuppressWarnings("unchecked")
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws IOException {        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);        
        gestor.replaceNode_isArray_nodeByIndex(stock_id-1); 
        gestor.replaceNode_findFieldByName(atributo_productosUnidad);
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_id, producto_id);
        if (gestor.isNodeNull()){
            return null;
        }else{                        
            gestor.replaceNode_findFieldByName(atributo_producto);
            return (ProductoCompleto) gestor.parseNodeToObject(ProductoCompleto.class); 
        }        
    }

    @Override
    public void eliminarProducto(ProductoCompleto producto) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);  
        int stock_index = producto.getCategoria().ordinal();
        gestor.replaceNode_isArray_nodeByIndex(stock_index);  
        gestor.replaceNode_findFieldByName(atributo_productosUnidad);    
        gestor.updateNode_isArray_deleteFirstElementByChildIntValue(atributo_id, producto.getProducto_id());       
        gestor.saveMainNodeInFile();
    }        
}
