package com.calvogasullmartin.t3_floristeria.persistencia.txt;

import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProductoCompletoTxt extends GenericDaoTxt<ProductoCompleto, Integer> implements ProductoCompletoDao{

    @Override
    public List<ProductoCompleto> getProductosSinUnidadesByStockId(int stock_id) throws IOException {
        gestor.setMainNode_FromFile();
        gestor.setAuxiliarNodesNull();
        String atributo_stocks = Floristeria.class.getDeclaredFields()[4].getName(); 
        gestor.setNode_findFieldByName_fromMain(atributo_stocks);        
        gestor.replaceNode_isArray_nodeByIndex(stock_id-1); //stock
        String atributo_productos = ConjuntoProductos.class.getDeclaredFields()[2].getName(); 
        gestor.replaceNode_findFieldByName(atributo_productos);//productos        
        ProductoUnidad[] productosUnidad = (ProductoUnidad[]) gestor.parseNodeToObject(ProductoUnidad[].class);        
        List<ProductoCompleto> productos = new LinkedList<>();
        for (ProductoUnidad productoUnidad : productosUnidad) {
            productos.add(productoUnidad.getProducto());
        }
        return productos;
    }

    @Override
    public ProductoCompleto findProductoEnAllStocksById(int producto_id) throws IOException {
        String stocksFieldName = Floristeria.class.getDeclaredFields()[4].getName(); 
        String idFieldName = ProductoCompleto.class.getDeclaredFields()[0].getName(); 
        String productosFieldName = ConjuntoProductos.class.getDeclaredFields()[2].getName(); 
        gestor.setMainNode_FromFile();
        //ProductoCompleto producto = (ProductoCompleto) gestor.findInFirstArrayNodeFirstObjectByChildIIntNodeValueField(stocksFieldName, producto_id,idFieldName, productosFieldName,ProductoCompleto.class);
        //return producto;
        return null;//PENDENT
    }
   
    
}
