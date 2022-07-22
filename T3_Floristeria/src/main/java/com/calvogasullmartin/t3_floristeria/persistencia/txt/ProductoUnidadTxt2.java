package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria2;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos2;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto2;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad2;
import java.io.IOException;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao2;

public class ProductoUnidadTxt2 extends GenericDaoTxt2<ProductoUnidad2, Integer> implements ProductoUnidadDao2 {

    private String atributo_stocks = Floristeria2.class.getDeclaredFields()[4].getName();

    private String atributo_productos = ConjuntoProductos2.class.getDeclaredFields()[2].getName();

    private String atributo_cantidad = ProductoUnidad2.class.getDeclaredFields()[1].getName();

    private String atributo_productoId = ProductoCompleto2.class.getDeclaredFields()[0].getName();

    @Override
    @SuppressWarnings("unchecked")
    public void createNuevoProductoUnidadEnStocks(ProductoUnidad2 productoUnidad, int stock_id) throws IOException {
        assert stock_id <= Categoria2.values().length && stock_id > 0;
        int maxActualId = findMaxProductId();        
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks 
        gestor.replaceNode_isArray_nodeByIndex(stock_id - 1);//stock  
        gestor.replaceNode_findFieldByName(atributo_productos);//productos         
        int assigned_id = productoUnidad.getProducto().getProducto_id();
        if (assigned_id > 0) {
            gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_productoId,assigned_id);   
            assert !gestor.isNodeNull();
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
        gestor.setListNodes_findAllFieldsByName(atributo_productoId); // all producto_id in stockS        
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
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_productoId, producto_id); //productoUnidad con ese id en producto
        if(gestor.isNodeNull()){ // not found
            return -1;
        }else{
           gestor.replaceNode_findFieldByName(atributo_cantidad);
           return gestor.getIntValue_fromNode(); 
        }        
    }

    @Override
    public void actualizarUnidadesProductoByStockId(ProductoUnidad2 producto, int idConjunto) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        gestor.setNode_findFieldByName_fromMain(atributo_stocks); //stocks array    
        gestor.replaceNode_isArray_nodeByIndex(idConjunto-1);  //stock
        gestor.replaceNode_findFieldByName(atributo_productos); //products array
        int id = producto.getProducto().getProducto_id();
        //product with that id , ya antes me he assegurado de que existe
        gestor.replaceNode_isArray_NodeIndexedWithChildIntValue(atributo_productoId,id);
        gestor.updateNode_setNewIntValueInField(atributo_cantidad, producto.getCantidad());
        gestor.saveMainNodeInFile(); 
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

    

}
