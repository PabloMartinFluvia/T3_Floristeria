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
    public void createProductoYAsociarloAlStockConUnidades(ProductoUnidad productWithUnits, int idStock) throws IOException {
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
    public ProductoUnidad findByStockIdAndProductoId(int stock_id,int producto_id) throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        /*
        go to node array (stocks)
        go to indexed node
        go to node array (products)
        iterate nodes indexed till first of them who whas a child fieldName with this id value
        return the node indexed
        */
    }
    
    @Override
    public int enCuantosTiquetsEsta(int producto_id) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        /*
        got to node array (tiquets)
        iterate nodes indexed till first of them who whas a child fieldName with this id value
        return 0 if not found (and >0 if found)
        */
    }
    
    @Override
    public void actualizarUnidadesProductoByStockId(ProductoUnidad producto, int idConjunt) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        /*
        go to node array (stocks)
        go to indexed node
        go to node array (products)
        iterate nodes indexed till first of them who whas a child fieldName with this id value
        parse entity to node
        update node inexed with new enitity node
        */
    }
    
    @Override
    public void deleteInStock(ProductoUnidad producto, int idConjunt) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //sabiendo en que stock está: eliminar producto y cantidades
        /*
        go to node array (stocks)
        go to indexed node
        go to node array (products)
        parse this node to ArrayNode
        recorrerlo mediante un for + if child idNode = id -> arraynode.remove(index of for)
        */
    }       
}
