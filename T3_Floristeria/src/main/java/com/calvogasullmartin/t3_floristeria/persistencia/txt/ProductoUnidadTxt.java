package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;

public class ProductoUnidadTxt extends GenericDaoTxt<ProductoUnidad, Integer> implements ProductoUnidadDao {

    private String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();
    
    private String atributo_tickets = Floristeria.class.getDeclaredFields()[5].getName();

    private String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();

    private String atributo_cantidad = ProductoUnidad.class.getDeclaredFields()[1].getName();

    private String atributo_productoId = ProductoCompleto.class.getDeclaredFields()[0].getName();

    @Override
    @SuppressWarnings("unchecked")
    public void createNuevoProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException {
        assert stock_id <= Categoria.values().length && stock_id > 0;
        int maxActualId = findMaxProductId(); 
        productoUnidad.getProducto().setProducto_id(maxActualId + 1);
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks 
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1);//stock  
        gestor.replaceNode_findFieldByName(atributo_productos);//productos
        gestor.setAuxiliarNode_ObjectInput(productoUnidad);
        gestor.updateNode_isArray_pushAuxiliarNode();       
        gestor.saveMainNodeInFile();      
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void reCatalogarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException {
        //idem al anterior, pero en este caso ya tiene el id assignado
        assert stock_id <= Categoria.values().length && stock_id > 0;
        assert productoUnidad.getProducto().getProducto_id() > 0;        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks 
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1);//stock  
        gestor.replaceNode_findFieldByName(atributo_productos);//productos
        gestor.setAuxiliarNode_ObjectInput(productoUnidad);
        gestor.updateNode_isArray_pushAuxiliarNode();       
        gestor.saveMainNodeInFile();   
    }

    private int findMaxProductId() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);//stocks        
        gestor.setListNodes_findAllFieldsByName(atributo_productoId); // all producto_id in stockS  
        int maxIdSotck = gestor.getMaxIntValue_fromListNodes();
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tickets);//stocks        
        gestor.setListNodes_findAllFieldsByName(atributo_productoId); // all producto_id in stockS  
        int maxIdTickets = gestor.getMaxIntValue_fromListNodes();
        return Math.max(maxIdSotck, maxIdTickets);
    }

    //si no existe la relación entre el producto y el stock -> el dao devuelve un número
    //negativo (equivalente a un null).
    @Override
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1); //stock  
        gestor.replaceNode_findFieldByName(atributo_productos);//productos        
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_productoId, producto_id); //productoUnidad con ese id en producto
        if(gestor.isNodeNull()){ // not found
            return -1;
        }else{
           gestor.replaceNode_findFieldByName(atributo_cantidad);
           return gestor.getIntValue_fromNode(); 
        }        
    }

    

    @Override
    public void incrementarCantidadByStockIdProductoId(int stockId, int productoId, int incremento) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks array    
        gestor.replaceNode_isArray_nodeByIndex(stockId-1);  //stock
        gestor.replaceNode_findFieldByName(atributo_productos); //products array        
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_productoId,productoId);
        gestor.updateNode_incrementIntValueInField(atributo_cantidad, incremento);
        gestor.saveMainNodeInFile();
    }        

    @Override
    public boolean isSoldAnytimeById(int productoId) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_tickets); //tickets array 
        gestor.setListNodes_findAllFieldsByName(atributo_productoId);
        return gestor.inListNodes_hasAnyIntValue(productoId);
    }
    
    @Override
    public void eliminarRelacionConStock(int stock_id, int producto_id) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks array    
        gestor.replaceNode_isArray_nodeByIndex(stock_id-1);  //stock
        gestor.replaceNode_findFieldByName(atributo_productos);  
        gestor.updateNode_isArray_deleteFirstElementByChildIntValue(atributo_productoId, producto_id);
        gestor.saveMainNodeInFile();
    }
}
