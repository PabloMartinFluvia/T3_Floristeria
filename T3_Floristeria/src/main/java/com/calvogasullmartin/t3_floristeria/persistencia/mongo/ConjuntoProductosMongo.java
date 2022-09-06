package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import com.mongodb.MongoException;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

public class ConjuntoProductosMongo extends GenericDaoMongo<ConjuntoProductos, Integer> implements ConjuntoProductosDao {

    /*
    Estructura de tickets en floristeria collection: (ídem para stocks)
            
        tiquets: [   //analogo a List de ConjuntoProductos        
            {
                conjunto_id:
                valor_Productos:
                productos : [ // analogo a List de ProductoUnidad
                    {
                        producto: // analogo a ProductoCompleto
                            {
                                producto_id:
                            }
                        cantidad:
                    }, etc...
                ]    
            }, etc...
        ]
    
        -> guardar: del ProductoCompleto solo se almacena la id
                resta de info en coleccion productos
        -> leer / obtener objetos: és necessario $lookup con la colección productos
    */
    
    
    @Override
    public void createTiquet(ConjuntoProductos tiquet) throws MongoException {
        assert tiquet != null;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        tiquet.setConjunto_id(findMaxTicketId()+1);
        setConjuntoDocument(tiquet);
        collection.updateOne(floristeriaFilter,push("tiquets", document));
        close();
    }
    
    private int findMaxTicketId() throws MongoException{
        projection = fields(include("tiquets"),excludeId());
        document = collection.aggregate(Arrays.asList(
                match(floristeriaFilter), // find document floristeria
                project(projection), //project only tiquets[]                        
                unwind("$tiquets"), //N docs separated for N tickets in floristeria        
                sort(descending("tiquets.conjunto_id"))
            )).first();        
        if(document != null){                
            return document.get("tiquets", Document.class).getInteger("conjunto_id");
        }else{
            return 0;
        }
    }
    
    @SuppressWarnings("unchecked")
    private void setConjuntoDocument(ConjuntoProductos conjunto){   
        assert conjunto != null;
        setDocumentFromJson(gestor.parseObjectToJson(conjunto));
        List<Document> productos = document.getList("productos", Document.class);
        Document productoCompleto;
        for(Document productoUnidad : productos){
            productoCompleto = productoUnidad.get("producto", Document.class);
            productoCompleto.remove("categoria");
            productoCompleto.remove("nombre");
            productoCompleto.remove("precio");
            productoCompleto.remove("altura");
            productoCompleto.remove("color");
            productoCompleto.remove("material");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ConjuntoProductos getOneStockById(int stock_id) throws MongoException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        String conjuntoName= "stocks";
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        document = collection.aggregate(Arrays.asList(
                match(floristeriaFilter),
                project(fields(excludeId(), include(conjuntoName))), //stocks []
                unwind("$"+conjuntoName),// N docs (one for each stock) (N = num Categorias)
                match(eq(conjuntoName+".conjunto_id", stock_id)), //1 doc (specific stock)
                //join producto info (ProductoCompleto object stored in productos collection)
                lookup(ConexionMongo.PRODUCTOS_COLL_NAME,
                        conjuntoName+".productos.producto.producto_id", 
                        "producto_id", "arr_fromProducts"),                
                project(exclude("arr_fromProducts._id")) //exclude _id from lookup stage
            
        )).first(); 
        document = modifyConjuntoDoc(document, conjuntoName);        
        ConjuntoProductos stock = (ConjuntoProductos) 
                gestor.parseJsonStringToObject(document.toJson(), ConjuntoProductos.class);                
        close();
        return stock;
    }

    private Document modifyConjuntoDoc(Document conjuntoDoc, String conjuntoName){
        List<Document> productosUnidad = conjuntoDoc.get(conjuntoName, Document.class)
                .getList("productos", Document.class);
        List<Document> joinedProducts = conjuntoDoc.getList("arr_fromProducts", Document.class);        
        int producto_id;
        for(Document productoUnidad: productosUnidad){
            producto_id = productoUnidad.get("producto", Document.class).getInteger("producto_id");             
            productoUnidad.replace("producto", getJoinedProductoDoc(producto_id, joinedProducts));
        }    
        conjuntoDoc.remove("arr_fromProducts");
        return conjuntoDoc.get(conjuntoName,Document.class);
    }
    
    private Document getJoinedProductoDoc(int producto_id, List<Document> joinedProducts){
        Document productoJoined = null;
        Iterator<Document> iterador = joinedProducts.iterator();
        boolean found = false;
        while(iterador.hasNext() && !found){
            Document doc = iterador.next();
            if(doc.getInteger("producto_id") == producto_id){
                found = true;
                productoJoined = doc;
            }
        }
        return productoJoined;
    }
    
    @Override
    public List<ConjuntoProductos> getAllStocks() throws MongoException {
        return getAllConjuntos("stocks");
    }
    
    @SuppressWarnings("unchecked")
    private List<ConjuntoProductos> getAllConjuntos(String conjuntoName) throws MongoException{
        assert conjuntoName != null;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
        documents = collection.aggregate(Arrays.asList(
                match(floristeriaFilter),
                project(fields(excludeId(), include(conjuntoName))), //tiquets or stocks []
                unwind("$"+conjuntoName),// N docs (one for each conjunto) (N = num Categorias)                                                                
                //join producto info (ProductoCompleto object stored in productos collection)
                lookup(ConexionMongo.PRODUCTOS_COLL_NAME,
                        conjuntoName+".productos.producto.producto_id", 
                        "producto_id", "arr_fromProducts"),     
                project(exclude("arr_fromProducts._id")) //exclude _id from lookup stage
            ));
        List<ConjuntoProductos> conjuntos = new LinkedList<>();
        ConjuntoProductos conjunto;
        for(Document conjuntoDoc: documents){
            document = modifyConjuntoDoc(conjuntoDoc, conjuntoName);
            conjunto = (ConjuntoProductos) 
                gestor.parseJsonStringToObject(document.toJson(), ConjuntoProductos.class); 
            conjuntos.add(conjunto);
        }
        close();
        return conjuntos;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ConjuntoProductos> getAllTiquets() throws MongoException {        
        return getAllConjuntos("tiquets");
    }

    @Override
    public void incrementarValorEnStockById(int stock_id, float incrementoValor) throws MongoException {
       assert stock_id >= 1 && stock_id <= Categoria.values().length;
       accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);
       int stock_index = stock_id -1; 
       update = inc("stocks."+stock_index+".valor_Productos", incrementoValor);
       collection.updateOne(floristeriaFilter, update);
       client.close();
    }    
}
