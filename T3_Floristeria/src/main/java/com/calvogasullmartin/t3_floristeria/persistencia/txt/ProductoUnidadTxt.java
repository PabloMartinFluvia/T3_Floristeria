package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import java.io.IOException;

public class ProductoUnidadTxt extends GenericDaoTxt<ProductoUnidad, Integer> implements ProductoUnidadDao{

    @Override
    @SuppressWarnings("unchecked")
    public void createProductoYAsociarloAlStockConUnidades(ProductoUnidad productoUnidad, int stock_id) throws IOException {
        assert stock_id <= Categoria.values().length && stock_id>0;
        productoUnidad.getProducto().setProducto_id(findMaxProductId()+1);        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();                
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks 
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1);//stock   
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.replaceNode_findFieldByName(atributo_productos);//productos      
        gestor.setAuxiliarNode_ObjectInput(productoUnidad);
        gestor.updateNode_isArray_pushAuxiliarNode(); 
        gestor.saveMainNodeInFile();
    }
    
    private int findMaxProductId() throws IOException{                        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);//stocks
        System.out.println(gestor.test());
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.setListNodes_findAllFieldsByName(atributo_producto_id); // all producto_id in stockS
        System.out.println(gestor.testList());
        return gestor.getMaxIntValue_fromListNodes();
    }
        
    @Override
    public ProductoUnidad findByStockIdAndProductoId(int stock_id,int producto_id) throws IOException{
        String nombreAtributoConjunto = Floristeria.class.getDeclaredFields()[4].getName(); 
        String nombreAtributoProductos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        String nombreAtributProductoId = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.setMainNode_FromFile();
        gestor.setInChildNode_x_getChildNode_ParentIsIndexedInArray
        (nombreAtributoConjunto, stock_id-1, nombreAtributoProductos);
        //en child node_x está el array d stocks
        boolean found = gestor.updateChildNode_x_IterateArrayNodeTillChildNodeHasIntValue
            (producto_id, nombreAtributProductoId);
        if(found){            
            return (ProductoUnidad) gestor.parseChildNode_x_toObject(ProductoUnidad.class);
        }else{
            return null;
        }
    }
    
    @Override
    public boolean isVendidoAlgunaVezByProductId(int producto_id) throws IOException {        
        /*
        got to node array (tiquets)
        iterate nodes indexed till first of them who whas a child fieldName with this id value
        return 0 if not found (and >0 if found)
        */
        gestor.setMainNode_FromFile();
        String atributo_tiquets = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_tiquets);//tiquets
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.setListNodes_findAllFieldsByName(atributo_producto_id);//List of products_id sold anytime
        return gestor.inListNodes_hasAnyIntValue(producto_id);        
    }
    
    @Override
    public boolean actualizarUnidadesProductoByStockId(ProductoUnidad producto, int idConjunto) throws IOException {
       
        /*
        go to node array (stocks)
        go to indexed node
        go to node array (products)
        iterate nodes indexed till first of them who whas a child fieldName with this id value
        parse entity to node
        update node inexed with new enitity node
        */
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks array        
        gestor.replaceNode_isArray_nodeByIndex(idConjunto-1);  //stock
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.replaceNode_findFieldByName(atributo_productos); //products array
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        int id = producto.getProducto().getProducto_id();
        boolean found = gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_producto_id,id);//product with that id
        if (found){
            gestor.updateNode_setNewFloatValueInField("cantidad", producto.getCantidad());
        }                
        gestor.saveMainNodeInFile();
        return found;
    }
    
    @Override
    public void deleteInStock(ProductoUnidad producto, int idConjunto) throws IOException {
        
        //sabiendo en que stock está: eliminar producto y cantidades
        /*
        go to node array (stocks)
        go to indexed node
        go to node array (products)
        parse this node to ArrayNode
        recorrerlo mediante un for + if child idNode = id -> arraynode.remove(index of for)
        */
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks array        
        gestor.replaceNode_isArray_nodeByIndex(idConjunto-1);  //stock
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.replaceNode_findFieldByName(atributo_productos); //products array        
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        int id = producto.getProducto().getProducto_id();
        gestor.updateNode_isArray_deleteFirstElementByChildIntValue(atributo_producto_id,id);        
        gestor.saveMainNodeInFile();
        //////////////////////////////////////////////
    }       
}
