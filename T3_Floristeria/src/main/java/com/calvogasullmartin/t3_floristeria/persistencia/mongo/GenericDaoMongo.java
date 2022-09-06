package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.persistencia.GenericDao;
import com.calvogasullmartin.t3_floristeria.utils.JsonManager;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

public class GenericDaoMongo <T, ID> implements GenericDao<T, ID> {
    
    protected JsonManager gestor;
    
    protected ConexionMongo conexioMongo;
    
    protected MongoClient client;
    
    protected MongoDatabase database;
    
    protected MongoCollection<Document> collection;
    
    protected Document document;
    
    protected Bson floristeriaFilter;
    
    protected AggregateIterable<Document> documents;
    
    protected Bson projection;
    
    protected Bson update;

    public GenericDaoMongo() {
        this.gestor = new JsonManager();
        this.conexioMongo = ConexionMongo.getInstance();   
        this.floristeriaFilter = eq("floristeria_id", 1);
    }       
    
    protected void accessCollection(String nameCollection){
        setDatabase();
        setCollection(nameCollection);
    }
    
    protected void setDatabase(){
        client = conexioMongo.getMongoClient();
        database = client.getDatabase(ConexionMongo.DB_NAME);
    }
    
    protected void setCollection(String nameCollection){
        collection = database.getCollection(nameCollection);
    }
    
    protected void close(){
        client.close();
    }
        
    protected void setDocumentFromJson(String json){                
        document = Document.parse(json);    
    }
}
