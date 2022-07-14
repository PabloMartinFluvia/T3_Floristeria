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
        String stocksFieldName = Floristeria.class.getDeclaredFields()[4].getName(); 
        String productosFieldName = ConjuntoProductos.class.getDeclaredFields()[2].getName(); 
        gestor.getMainNodeFromFile();
        gestor.setInChildNode_x_getChildNode_ParentIsIndexedInArray(stocksFieldName, stock_id, productosFieldName);
        ProductoUnidad[] productosUnidad = (ProductoUnidad[]) gestor.getFromChildNode_x_parseNodeArrayToObjectArray(ProductoUnidad[].class);       
        List<ProductoCompleto> productos = new LinkedList<>();
        for (ProductoUnidad productosUnidad1 : productosUnidad) {
            productos.add(productosUnidad1.getProducto());
        }
        return productos;
    }

    @Override
    public ProductoCompleto findProductoEnAllStocksById(int producto_id) throws IOException {
        String stocksFieldName = Floristeria.class.getDeclaredFields()[4].getName(); 
        String idFieldName = ProductoCompleto.class.getDeclaredFields()[0].getName(); 
        String productosFieldName = ConjuntoProductos.class.getDeclaredFields()[2].getName(); 
        gestor.getMainNodeFromFile();
        ProductoCompleto producto = (ProductoCompleto) gestor.findInFirstArrayNodeFirstObjectByChildIIntNodeValueField(stocksFieldName, producto_id,idFieldName, productosFieldName,ProductoCompleto.class);
        return producto;
    }
   
    
}
