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
    public void createProductoYAsociarloAlStockConUnidades(ProductoUnidad productoUnidad, int idStock) throws IOException {
        assert idStock <= Categoria.values().length;
        int max_id = findMaxProductId();
        productoUnidad.getProducto().setProducto_id(max_id + 1);
        String nombreAtributoConjunto = Floristeria.class.getDeclaredFields()[4].getName(); 
        String nombreAtributoProductos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.getMainNodeFromFile();
        
        gestor.goToEspecificObjectInArrayInFileAndAddObjectToNestedArrayAndSave(productoUnidad,nombreAtributoConjunto, idStock-1, nombreAtributoProductos, Floristeria.class);
        gestor.saveMainNodeInFile();
    }
    
    private int findMaxProductId() throws IOException{
        String nombreAtributoId = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.getMainNodeFromFile();
        return gestor.findMaxIntValueInMultipleChildNodes(nombreAtributoId);
    }

    @Override
    public ProductoUnidad findByStockIdAndProductoId(int stock_id,int producto_id) throws IOException{
        String nombreAtributoConjunto = Floristeria.class.getDeclaredFields()[4].getName(); 
        String nombreAtributoProductos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        String nombreAtributProductoId = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.getMainNodeFromFile();
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
        gestor.getMainNodeFromFile();
        String atributo_tiquets = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setAuxiliarNode_findFieldByName(atributo_tiquets);//tiquets
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        gestor.setListAuxiliarNodes_findAllFieldsByName(atributo_producto_id);//List of products_id sold anytime
        return gestor.inAuxiliarNodes_hasAnyIntValue(producto_id);        
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
        gestor.getMainNodeFromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setAuxiliarNode_findFieldByName(atributo_stocks); //stocks array
        gestor.replaceAuxiliarNode_nodeInArrayByIndex(idConjunto-1);  //stock
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.replaceAuxiliarNode_findFieldByName(atributo_productos); //products array
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        int id = producto.getProducto().getProducto_id();
        boolean found = gestor.replaceAuxliarNode_nodeInArrayWithChildIntValue(atributo_producto_id,id);//product with that id
        if (found){
            gestor.upadteAuxiliarNode_setNewFloatValueInField("cantidad", producto.getCantidad());
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
        gestor.getMainNodeFromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
        gestor.setAuxiliarNode_findFieldByName(atributo_stocks); //stocks array
        gestor.replaceAuxiliarNode_nodeInArrayByIndex(idConjunto-1);  //stock
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.replaceAuxiliarNode_findFieldByName(atributo_productos); //products array
        gestor.setArrayAuxiliarNode_fromAuxiliarNode();
        String atributo_producto_id = ProductoCompleto.class.getDeclaredFields()[0].getName();
        int id = producto.getProducto().getProducto_id();
        gestor.updateArrayAuxiliarNode_deleteFirstElementByCildIntValue(atributo_producto_id,id);        
        gestor.saveMainNodeInFile();
        //////////////////////////////////////////////
    }       
}
