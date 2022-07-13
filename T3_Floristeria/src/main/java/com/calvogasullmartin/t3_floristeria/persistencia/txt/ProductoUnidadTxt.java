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
    public void addNuevoProductoYAsociarloAlStockConUnidades(ProductoUnidad productWithUnits, int idStock) throws IOException {
        assert idStock <= Categoria.values().length;
        int max_id = findMaxProductId();
        productWithUnits.getProducto().setProducto_id(max_id + 1);
        String nombreAtributoConjunto = Floristeria.class.getDeclaredFields()[4].getName(); 
        String nombreAtributoProductos = ConjuntoProductos.class.getDeclaredFields()[2].getName();
        gestor.goToEspecificObjectInArrayInFileAndAddObjectToNestedArrayAndSave(productWithUnits,nombreAtributoConjunto, idStock-1, nombreAtributoProductos, Floristeria.class);
    }
    
    private int findMaxProductId() throws IOException{
        String nombreAtributoId = ProductoCompleto.class.getDeclaredFields()[0].getName();
        return gestor.findMaxIntInMultipleFieldsWithSameName(nombreAtributoId);
    }

    
    
    
    @Override
    public void deleteInConjunto(ProductoUnidad producto, int idConjunt) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateInConjunto(ProductoUnidad producto, int idConjunt) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductoUnidad findByStockIdAndProductoId(int stock_id, int productoCompleto_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
