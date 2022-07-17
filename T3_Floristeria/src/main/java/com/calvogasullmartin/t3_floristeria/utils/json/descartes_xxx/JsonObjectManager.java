package com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigInteger;

public class JsonObjectManager<T> {
    private final JsonArrayNodeManager<T> arrayNodeManager;
    private ObjectMapper mapper;
    private Gson gson;
    
    private JsonNode mainNode;
    private JsonNode valueNode_n1;
    private ObjectNode objectNode_n1;
    private ArrayNode arrayNode_n1;
    private JsonNode valueNodeIndexedInArray_n1;
    private ObjectNode objectNodeIndexedInArray_n1;
    private ArrayNode arrayNodeIndexedInArray_n1; //when arrayNode_n1 is List<List<T>> -> to acces some List<T>
    
    public JsonObjectManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.arrayNodeManager = new JsonArrayNodeManager<>();
    }
    
    public JsonNode overrideChildNodeField_N1_floatType(JsonNode parentNode, String childFieldName_n1,float newValue){
        ((ObjectNode)parentNode).put(childFieldName_n1, newValue);
        return parentNode;
    }
}
