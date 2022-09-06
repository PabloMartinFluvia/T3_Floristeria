package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMongo {
    
    private static ConexionMongo conexionMongo = null;
    
    public final static String DB_NAME = "floristeria";
    
    public final static String FLORISTERIA_COLL_NAME = "floristeria";
    
    public final static String PRODUCTOS_COLL_NAME = "productos";
    
    private static String uri;        
    
    private ConexionMongo(){        
    }
    
    public static ConexionMongo getInstance(){
        if(conexionMongo == null){
            configureLogs();
            conexionMongo = new ConexionMongo();
        }
        return conexionMongo;
    }
    
    public MongoClient getMongoClient() throws MongoException{
        MongoClient client = MongoClients.create(uri);
        return client;
    }
    
    public static void setConexionValues(String arg){
        assert conexionMongo == null;
        assert arg != null;
        uri = arg;        
    }
    
    private static void configureLogs() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
    }
}
