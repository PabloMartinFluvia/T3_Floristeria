package com.calvogasullmartin.t3_floristeria.persistencia.mongo;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import com.mongodb.MongoException;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

public class FloristeriaMongo extends GenericDaoMongo<Floristeria, Integer> implements FloristeriaDao {

    @Override
    @SuppressWarnings("unchecked")
    public void create(Floristeria floristeria) throws MongoException {  
        assert floristeria != null;
        floristeria.setFloristeria_id(1);
        setDocumentFromJson(gestor.parseObjectToJson(floristeria));
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);     
        collection.insertOne(document);        
        close();
    }

    @Override
    public String getName() throws MongoException{
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME);                          
        projection = fields(include("nombre_floristeria"),excludeId());
        document = collection.find(floristeriaFilter).projection(projection).first(); 
        checkDocFound();
        String name = document.getString("nombre_floristeria");        
        close();
        return name;
    }
    
    private void checkDocFound() throws MongoException{
        if(document == null){
            throw new MongoException("Error. Floristeria document badly inizialized");
        }
    }

    @Override
    public float getValorFloristeria() throws MongoException {
        float value = getFloatValue("valorStocksTotal");        
        close();
        return value;
    }
    
    @Override
    public float getFacturacionFloristeria() throws MongoException {
        float value = getFloatValue("valorTicketsTotal");       
        close();
        return value;
    }
    
    private float getFloatValue(String field) throws MongoException{
        assert field != null;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME); 
        projection = fields(include(field),excludeId());        
        document = collection.find(floristeriaFilter).projection(projection).first(); 
        checkDocFound();
        return document.getDouble(field).floatValue();
    }

    @Override
    public void incrementarValorFloristeria(float incrementoValor) throws MongoException {
        incrementTotalField("valorStocksTotal", incrementoValor);
    }

    @Override
    public void incrementarFacturacionFloristeria(float incrementoValor) throws MongoException{
        incrementTotalField("valorTicketsTotal", incrementoValor);
    }
    
    private void incrementTotalField (String fieldName, float increment) throws MongoException {
        assert fieldName != null;
        accessCollection(ConexionMongo.FLORISTERIA_COLL_NAME); 
        update = inc(fieldName, increment);
        UpdateResult updateResult = collection.updateOne(floristeriaFilter, update);
        if(updateResult.getMatchedCount() != 1){
            throw new MongoException("Error. Floristeria document badly inizialized");
        }
        client.close();
    }
    
}
