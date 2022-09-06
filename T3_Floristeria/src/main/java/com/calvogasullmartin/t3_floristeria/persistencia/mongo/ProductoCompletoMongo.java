package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import com.mongodb.MongoException;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ProductoCompletoMongo extends GenericDaoMongo<ProductoCompleto, Integer> implements ProductoCompletoDao {

    private Bson productoFilter;
    
    @Override
    public int findId(ProductoCompleto producto) throws MongoException {
        assert producto != null;
        assert producto.getAltura() != null || producto.getColor() != null || producto.getMaterial() != null;
        accessCollection(ConexionMongo.PRODUCTOS_COLL_NAME);         
        productoFilter = and(
                eq("categoria", producto.getCategoria().name()),
                eq("nombre", producto.getNombre()),
                eq("precio", producto.getPrecio())
        );
        completeFilter(producto);
        projection = fields(include("producto_id"),excludeId());
        document = collection.find(productoFilter).projection(projection).first(); 
        int id = 0;
        if (document != null){
            id = document.getInteger("producto_id");
        }
        client.close();
        return id;
    }
    
    private void completeFilter(ProductoCompleto producto){  
        assert producto.getCategoria() != null;
        Categoria categoria = producto.getCategoria();
        Bson arbolFilter = null;
        Bson colorFilter = null;
        Bson materialFilter = null;
        switch(categoria){
            case ARBOL -> { 
                arbolFilter = eq("altura", producto.getAltura().name());
                colorFilter = exists("color", false);
                materialFilter = exists("material", false);
            }
            case FLOR -> {
                arbolFilter = exists("altura", false);
                colorFilter = eq("color", producto.getColor());
                materialFilter = exists("material", false);
            }
            case DECORACION -> {    
                arbolFilter = exists("altura", false);
                colorFilter = exists("color", false);
                materialFilter = eq("material", producto.getMaterial().name());
            }
        }        
        productoFilter = and(productoFilter, arbolFilter, colorFilter, materialFilter);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws MongoException {
        assert producto_id > 0;
        assert stock_id > 0 && stock_id <= Categoria.values().length;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);        
        document = collection.aggregate(Arrays.asList(
                match(floristeriaFilter), // find document floristeria
                project(fields( //project specific stock's document (by stock_id -> index)
                        excludeId(),                             
                        computed("stock", eq("$arrayElemAt",  Arrays.asList("$stocks", stock_id -1))))),
                unwind("$stock.productos"), //N docs separated for N productoUnidad in stock        
                match(eq("stock.productos.producto.producto_id", producto_id)), // doc with the product ref
                //join product info (form productos collection)
                lookup(ConexionMongo.PRODUCTOS_COLL_NAME, "stock.productos.producto.producto_id",
                        "producto_id","arr_fromProducts"),
                project(include("arr_fromProducts")) // array[] docs productosCompletos joined               
            )).first();                
        ProductoCompleto producto = null;
        if(document != null){
            document = document.getList("arr_fromProducts", Document.class).get(0);
            document.remove("_id"); 
            producto = (ProductoCompleto) 
                    gestor.parseJsonStringToObject(document.toJson(), ProductoCompleto.class);
        }        
        close();
        return producto;
    }

    @Override
    public void eliminarProducto(ProductoCompleto producto) throws MongoException {
        assert producto != null;
        assert producto.getProducto_id() > 0;
        int producto_id = producto.getProducto_id();
        accessCollection(ConexionMongo.PRODUCTOS_COLL_NAME);
        productoFilter = eq("producto_id", producto_id);
        collection.deleteOne(productoFilter);
        setCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        productoFilter = eq("producto.producto_id", producto_id);
        int stock_index = producto.getCategoria().ordinal();        
        update = pull("stocks."+stock_index+".productos", productoFilter);
        collection.updateOne(floristeriaFilter, update);        
        close();
    }
    
}
