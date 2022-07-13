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
        ProductoUnidad[] productosUnidad = (ProductoUnidad[]) gestor.goToSpecificIndexInUniqueArrayAndGetNestedCollection(stocksFieldName, stock_id-1,productosFieldName , ProductoUnidad[].class);
        List<ProductoCompleto> productos = new LinkedList<>();
        for (ProductoUnidad productosUnidad1 : productosUnidad) {
            productos.add(productosUnidad1.getProducto());
        }
        return productos;
    }

    @Override
    public ProductoCompleto findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
