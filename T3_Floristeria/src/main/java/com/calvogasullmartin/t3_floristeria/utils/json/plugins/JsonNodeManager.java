package com.calvogasullmartin.t3_floristeria.utils.json.plugins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonNodeManager <T>{
    
    private final JsonArrayNodeManager<T> arrayNodeManager;
    private ObjectMapper mapper;
    private Gson gson;
    
    public JsonNodeManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.arrayNodeManager = new JsonArrayNodeManager<>();
    }
    
    //////////////////////////////////////////////////////////////////////
    //parse NODE VS OBJECT
    //////////////////////////////////////////////////////////////////
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada
     * @param node
     * @param claseDeLaEntidad
     * @return 
    */
    public T parseNodeToObject(JsonNode node, Class<T> claseDeLaEntidad){  
        assert node != null;
        String json = node.toPrettyString();
        T object = gson.fromJson(json, claseDeLaEntidad);        
        return object;        
    }
    
    
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada
     * @param nodeIsArray
     * @param claseEntidad
     * @return 
     *
    */
    public T[] parseNodeArrayToObjectArray(JsonNode nodeIsArray, Class<T[]> claseEntidad){  
        assert nodeIsArray != null;
        String json = nodeIsArray.toPrettyString();
        T[] object = gson.fromJson(json, claseEntidad);        
        return object;        
    }
    
    public JsonNode parseObjectToNode(T object){
        return mapper.convertValue(object, JsonNode.class);
    }
    
    
    //////////////////////////////////////////////////////////////////////
    //GET FIELDS from node 
    //////////////////////////////////////////////////////////////////
    
    /**
     * Checks the node tree, and returns a sorted list with the names of the main
     * nodes / top level fields.
     * 
     * @param nodo
     * @return
     */
    public List<String> getMainFieldsNamesFromJsonNode(JsonNode nodo) {        
        Iterator<String> fields = nodo.fieldNames();// only the top level fields
        List<String> listaFieldsNames = new LinkedList<>();
        while (fields.hasNext()){
            listaFieldsNames.add(fields.next());
        }
        return listaFieldsNames;
    }
    
    ////////////////////////////////////////////////
    ///FIND NODES OF FIELDS IN NODE
    ///////////////////////////////////////////////
    
    /**
     * busca también en los hijos, mejor si el campo buscado puede NO ser un valor (array, embebed)
     * Ojo: si el valor es null -> devuelve un NullNode
     * @param parentNode
     * @param fieldName
     * @return 
     */
    public JsonNode findFirstNodeFieldThatMatchesFieldName (JsonNode parentNode, String fieldName){
        return parentNode.get(fieldName);
    }
    
    /**
     * Devuelve una lista de todos los atributos con ese nombre que haya dentro
     * del nodo, incluyendo los hijos.
     * Ojo: a la que encuentra un atributo con ese nombre YA NO BUSCA MÁS EN LOS HIJOS
     * @param parentNode
     * @param fieldName
     * @return 
     */
    public List<JsonNode> finAllNodes (JsonNode parentNode, String fieldName){
        return parentNode.findValues(fieldName);
    }
    
    /**
     * busca también en los hijos, mejor si el campo buscado es un valor
     * @param parentNode
     * @param fieldName
     * @return 
     */
    public JsonNode findFirstNodeValueFieldThatMatchesFieldName (JsonNode parentNode, String fieldName){
        return parentNode.findValue(fieldName);
    }
    
     ///////////////////////////////////////////////////////
    //UPDATE NODES
    //////////////////////////////////////////////////
    
    /**
     * Pasando un nodo padre, pon un nuevo float en alguno de sus campos (de tipo float)
     * ?? funciona si el nodo padre que se pasa como argumento es un subnodo del objeto leido???
     * 
     * @param parentNode parent node
     * @param fieldName
     * @param increment
     * @return
     */
    public JsonNode incrementTopLevelFloatField(JsonNode parentNode, String fieldName, float increment){        
        assert parentNode != null;
        float newValue = findFirstNodeValueFieldThatMatchesFieldName(parentNode, fieldName).floatValue()+increment;                 
        ((ObjectNode)parentNode).put(fieldName, newValue);        
        return parentNode;
    }
    
    public JsonNode goToEspecificObjectInArrayAndAddObjectToNestedArray(JsonNode mainNode,T newElement, String fieldArray, int arrayIndex, String fieldNestedArray){
        JsonNode array = findFirstNodeFieldThatMatchesFieldName(mainNode, fieldArray);
        if (array.isArray()){            
            JsonNode nodeIndexed = arrayNodeManager.getNodeInArrayByIndex(array, arrayIndex);
            JsonNode arrayNested = findFirstNodeFieldThatMatchesFieldName(nodeIndexed, fieldNestedArray);                        
            JsonNode newNode = parseObjectToNode(newElement);
            arrayNested = arrayNodeManager.pushNodeInArrayNode(arrayNested, newNode);                        
        }
        return mainNode;
    }
}
