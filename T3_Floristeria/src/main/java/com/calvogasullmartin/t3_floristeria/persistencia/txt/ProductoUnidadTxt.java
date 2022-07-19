package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import java.io.IOException;

public class ProductoUnidadTxt extends GenericDaoTxt<ProductoUnidad, Integer> implements ProductoUnidadDao {

    private String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName();

    private String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName();

    private String atributo_cantidad = ProductoUnidad.class.getDeclaredFields()[1].getName();

    private String atributo_id = ProductoCompleto.class.getDeclaredFields()[0].getName();

    @Override
    @SuppressWarnings("unchecked")
    public void createNuevoProductoUnidadEnStocks(ProductoUnidad productoUnidad, int stock_id) throws IOException {
        assert stock_id <= Categoria.values().length && stock_id > 0;
        int maxActualId = findMaxProductId();        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks 
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1);//stock  
        gestor.replaceNode_findFieldByName(atributo_productos);//productos         
        int assigned_id = productoUnidad.getProducto().getProducto_id();
        if (assigned_id > 0) {
            gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_id,assigned_id);            
            gestor.updateNode_setNewIntValueInField(atributo_cantidad, productoUnidad.getCantidad());                 
        }else{
            productoUnidad.getProducto().setProducto_id(maxActualId + 1);
            gestor.setAuxiliarNode_ObjectInput(productoUnidad);
            gestor.updateNode_isArray_pushAuxiliarNode();            
        }
        gestor.saveMainNodeInFile();
    }

    private int findMaxProductId() throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);//stocks        
        gestor.setListNodes_findAllFieldsByName(atributo_id); // all producto_id in stockS        
        return gestor.getMaxIntValue_fromListNodes();
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
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_id, producto_id); //productoUnidad con ese id en producto
        gestor.replaceNode_findFieldByName(atributo_cantidad);
        return gestor.getIntValue_fromNode();
    }

}
