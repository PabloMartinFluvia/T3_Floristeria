package com.calvogasullmartin.t3_floristeria.utils.json.plugins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonArrayNodeManager <T>{

    private ObjectMapper mapper;
    private Gson gson;
    
    public JsonArrayNodeManager() {
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
    }
    
    
    public JsonNode getNodeInArrayByIndex(ArrayNode arrayNode, int indexArray){
        return arrayNode.get(indexArray);
    }        
    
    public JsonNode pushNodeInArrayNode(ArrayNode arrayNode, JsonNode newNode){
        return arrayNode.add(newNode);
    }        
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada.
     * T = ObjectClass[]
     * @param arrayNode
     * @param arrayClass
     * @return 
    */
    public T parseNodeArrayToObjectArray(ArrayNode arrayNode, Class<T> arrayClass){  //NombreClasse[].class
        assert arrayNode != null;
        String json = arrayNode.toPrettyString();
        T arrayObject = gson.fromJson(json, arrayClass);        
        return arrayObject;        
    }

    
}
