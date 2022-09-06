package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import com.mongodb.MongoException;
import static com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.ReturnDocument;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ProductoUnidadMongo extends GenericDaoMongo<ProductoUnidad, Integer> implements ProductoUnidadDao {
    
            
    @Override
    @SuppressWarnings("unchecked")
    public void createNuevoProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws MongoException {
        assert productoUnidad != null;
        assert stock_id >0 && stock_id <= Categoria.values().length;
        accessCollection(ConexionMongo.PRODUCTOS_COLL_NAME);
        productoUnidad.getProducto().setProducto_id(findMaxProductoId()+1);
        setDocumentFromJson(gestor.parseObjectToJson(productoUnidad.getProducto()));        
        collection.insertOne(document);        
        associateProductWithConjunto("stocks",productoUnidad, stock_id-1);
        close();
    }
    
    private int findMaxProductoId() throws MongoException{
        document = collection.find().sort(descending("producto_id")).limit(1).first();
        if(document != null){
            return document.getInteger("producto_id");
        }else{
            return 0;
        }
    }

    private void setProductoUnidadDocument(ProductoUnidad productoUnidad){
        assert productoUnidad != null;
        document = new Document();
        //store info in floristeria collection (in stocks or tickets) only quantity and id product
        //  use $lookup for more info of the product
        document.append("producto", new Document("producto_id",productoUnidad.idProducto()))
                .append("cantidad", productoUnidad.getCantidad());
    }
    
    private void associateProductWithConjunto(String conjuntoName, ProductoUnidad productoUnidad, int conj_idx) throws MongoException {
        assert conjuntoName.equals("stocks") || conjuntoName.equals("tickets");
        assert productoUnidad != null;
        assert conj_idx >=0 && conj_idx < Categoria.values().length;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        setProductoUnidadDocument(productoUnidad);
        update = push(conjuntoName+"."+conj_idx+".productos", document);
        collection.updateOne(floristeriaFilter, update);
    }
    
    @Override
    public void reCatalogarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws IOException, SQLException {
        assert productoUnidad != null;
        assert stock_id >0 && stock_id <= Categoria.values().length;
        associateProductWithConjunto("stocks",productoUnidad, stock_id-1);
        close();
    }

    @Override
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws MongoException {
        assert stock_id >0 && stock_id <= Categoria.values().length;
        assert producto_id > 0;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        document = collection.aggregate(Arrays.asList(
                match(floristeriaFilter), // find document floristeria
                project(fields( //project specific stock's document (by stock_id -> index)
                        excludeId(),                             
                        computed("stock", eq("$arrayElemAt",  Arrays.asList("$stocks", stock_id -1))))),
                unwind("$stock.productos"), //N docs separated for N productoUnidad in stock        
                match(eq("stock.productos.producto.producto_id", producto_id)), // doc with the product ref                
                project(fields(
                        excludeId(),                             
                        eq("cantidad", "$stock.productos.cantidad")))
            )).first();
        int cantidad = -1;
        if(document != null){
            cantidad = document.getInteger("cantidad");
        }
        close();
        return cantidad;
    }

    @Override
    public void incrementarCantidadByStockIdProductoId(int stock_id, int producto_id, int increment) throws MongoException {        
        assert stock_id >0 && stock_id <= Categoria.values().length;
        assert producto_id > 0;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME); 
        int stock_idx = stock_id -1;              
        Bson filter = and(floristeriaFilter,
                Filters.elemMatch
                    ("stocks."+stock_idx+".productos",eq("producto.producto_id", producto_id))
                );        
        Bson arrayFilter = eq("elem.producto.producto_id", producto_id);          
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .arrayFilters(Arrays.asList(arrayFilter));
        
        update = inc("stocks."+stock_idx+".productos.$[elem].cantidad",increment);
        collection.findOneAndUpdate(filter, update, options);
        close();
    }

    @Override
    public boolean isSoldAnytimeById(int producto_id) throws MongoException {
        assert producto_id > 0;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);        
        Bson filter = and(floristeriaFilter,
                Filters.elemMatch
                    ("tiquets.productos",eq("producto.producto_id", producto_id))
                );
        document = collection.find(filter).first();
        close();
        return document != null;
    }

    @Override
    public void eliminarRelacionConStock(int stock_id, int producto_id) throws MongoException {
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);         
        Bson pullFilter = eq("producto.producto_id", producto_id);
        int stock_index = stock_id -1;       
        update = pull("stocks."+stock_index+".productos", pullFilter);
        collection.updateOne(floristeriaFilter, update);        
        close();
    }    
}
