package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.persistencia.ValidadorBD;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;

public class ValidadorBDMongo implements ValidadorBD{
    
    protected ConexionMongo conexio;

    public ValidadorBDMongo() {
        conexio = ConexionMongo.getInstance();
    }

    @Override
    public boolean isBDInicizialized() throws MongoException {
        MongoClient client = conexio.getMongoClient();
        boolean found = false;
        for (String name : client.listDatabaseNames()) {
            if(name.equals(ConexionMongo.DB_NAME)){
                found = true;
                break;
            }
        }
        client.close();
        return found;
    }

    @Override
    public void initBD() throws MongoException {
        /*
        PENDENT create collection amb schema validator
            floristeria
            productos
        */
    }
    
}
