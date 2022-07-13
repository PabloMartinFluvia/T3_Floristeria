package com.calvogasullmartin.t3_floristeria.utils.json.plugins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonArrayNodeManager <T>{

    private ObjectMapper mapper;
    private Gson gson;
    
    public JsonArrayNodeManager() {
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
    }
    
    
    public JsonNode getNodeInArrayByIndex(JsonNode arrayNode, int indexArray){
        return arrayNode.get(indexArray);
    }
    
    public JsonNode pushNodeInArrayNode(JsonNode arrayNode, JsonNode newNode){
        return ((ArrayNode)arrayNode).add(newNode);
    }
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada
     * @param arrayNode
     * @param arrayClass
     * @return 
    */
    public T parseNodeArrayToObjectArray(JsonNode arrayNode, Class<T> arrayClass){  //NombreClasse[].class
        assert arrayNode != null;
        String json = arrayNode.toPrettyString();
        T arrayObject = gson.fromJson(json, arrayClass);        
        return arrayObject;        
    }

    
}
