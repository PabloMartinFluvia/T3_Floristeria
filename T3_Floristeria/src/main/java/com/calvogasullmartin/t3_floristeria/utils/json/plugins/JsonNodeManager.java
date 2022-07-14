package com.calvogasullmartin.t3_floristeria.utils.json.plugins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonNodeManager <T>{
    
    private final JsonArrayNodeManager<T> arrayNodeManager;
    private final JsonObjectManager<T> objectNodeManager;
    private ObjectMapper mapper;
    private Gson gson;
    
    private JsonNode mainNode;
    private JsonNode valueNode_n1;
    private ObjectNode objectNode_n1;
    private ArrayNode arrayNode_n1;
    private JsonNode valueNodeIndexedInArray_n1;
    private ObjectNode objectNodeIndexedInArray_n1;
    private ArrayNode arrayNodeIndexedInArray_n1; //when arrayNode_n1 is List<List<T>> -> to acces some List<T>
    
    public JsonNodeManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.arrayNodeManager = new JsonArrayNodeManager<>();
        this.objectNodeManager = new JsonObjectManager<>();
    }
    
    //////////////////////////////////////////////////////////////////////
    //parse NODE VS OBJECT
    //////////////////////////////////////////////////////////////////
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada
     * @param node
     * @param classOfObject
     * @return 
    */
    public T parseNodeToObject(JsonNode node, Class<T> classOfObject){  
        assert node != null;
        String json = node.toPrettyString();
        T object = gson.fromJson(json, classOfObject);        
        return object;        
    }
        
    
    /**
     * Tinguent un node pare -> transformarlo a un objecte d'una clase determinada
     * @param arrayNode
     * @param classOfArrayObject
     * @return 
     *
    */
    public T[] parseNodeArrayToObjectArray(JsonNode arrayNode, Class<T[]> classOfArrayObject){  
        assert arrayNode != null;
        String json = arrayNode.toPrettyString();
        T[] object = gson.fromJson(json, classOfArrayObject);        
        return object;        
    }
    
    public JsonNode parseObjectToNode(T object){
        return mapper.convertValue(object, JsonNode.class);
    }
    
    
    //////////////////////////////////////////////////////////////////////
    //GET FIELDS NAMES from node 
    //////////////////////////////////////////////////////////////////
    
    /**
     * Checks the node tree, and returns a sorted list with the names of the main
     * nodes / top level fields.
     * 
     * @param parentNode
     * @return
     */
    public List<String> getMainFieldsNamesFromJsonNode(JsonNode parentNode) {        
        Iterator<String> iterator = parentNode.fieldNames();// only the top level fields
        List<String> childFieldsNamesList_onlyN1 = new LinkedList<>();
        while (iterator.hasNext()){
            childFieldsNamesList_onlyN1.add(iterator.next());
        }
        return childFieldsNamesList_onlyN1;
    }
    
    ////////////////////////////////////////////////
    ///FIND CHLIDS NODES BY FIELD NAME
    ///////////////////////////////////////////////
    
    /**
     * busca en todo el arbol de hijos, mejor si el campo buscado puede NO ser un 
     * valor (array, embebed, object, document)
     * Ojo: si el valor del campo es null -> devuelve un NullNode
     * @param parentNode
     * @param childFieldName
     * @return 
     */
    public JsonNode findFirstChildObjectNodeByChildNodeFieldName (JsonNode parentNode, String childFieldName){
        return parentNode.get(childFieldName);
    }
    
    /**
     * busca en todo el arbol de hijos, mejor si el campo buscado es un valor
     * @param parentNode
     * @param childFieldName
     * @return 
     */
    public JsonNode findFirstChildValueNodeByChildNodeFieldName (JsonNode parentNode, String childFieldName){
        return parentNode.findValue(childFieldName);
    }
    
    /**
     * Devuelve una lista de todos los atributos con ese nombre que haya dentro
     * del nodo, incluyendo los hijos.
     * Ojo: a la que encuentra un atributo con ese nombre YA NO BUSCA MÁS EN LOS HIJOS
     * @param parentNode
     * @param childFieldName
     * @return 
     */
    public List<JsonNode> finAllChildNodesByChildNodeFieldName (JsonNode parentNode, String childFieldName){
        return parentNode.findValues(childFieldName);
    }
    
    ////////////////////////////////////////////////
    ///FIND CHLIDS VALUES BY FIELD NAME
    ///////////////////////////////////////////////
    
    /** Get the string value of the node. If the node is null or his valie
     * is not value node -> return null
     * Get no
     * @param node
     * @return 
     */
    public String getStringValueOfNode(JsonNode node){
        if (node != null && node.isValueNode()){ //node not NULL && value: String, Numbre, Boolean, NULL
            return node.asText();
        }else {
            return null;
        }
    }
    
    /** Get the float value of the node. If the node is null or his valie
     * is not a number -> return null
     * Get no
     * @param node
     * @return 
     */
    public Float getFloatValueOfNode(JsonNode node){
         if (node != null && node.isNumber()){ //node not NULL && value is numberic                        
            return node.floatValue();
        }else {
            return null;
        } 
    }
    
    /** Get the int value of the node. If the node is null or his valie
     * is not a int -> return null
     * Get no
     * @param node
     * @return 
     */
    public Integer getIntValueOfNode(JsonNode node){
         if (node != null && node.isInt()){ 
            return node.asInt();
        }else {
            return null;
        } 
    }
    
    ////////////////////////////////////////////////
    ///FIND CHLIDS NODES BY VALUE
    ///////////////////////////////////////////////
    
    /**
     * Find first child node (with specific field name) which has an specicfic int value.
     * If not found returns null;
     * @param parentNode
     * @param childFieldName
     * @param childNodeValue
     * @return 
     */
    public JsonNode findFirstChildNodeWithSpecificIntValue(JsonNode parentNode, String childFieldName, int childNodeValue){
        List<JsonNode> nodos = finAllChildNodesByChildNodeFieldName(parentNode, childFieldName);
        boolean found = false;
        Iterator<JsonNode> iterator = nodos.iterator();
        JsonNode childNode = null;
        while (!found && iterator.hasNext()){
            childNode = iterator.next();
            int idValue = getIntValueOfNode(childNode);
            if (idValue == childNodeValue){
                found = true;
            }
        }
        if (!found){
            return null;
        }else{
            return childNode;
        }
    }
    
     ///////////////////////////////////////////////////////
    //UPDATE NODES
    //////////////////////////////////////////////////
    
    /**
     * Pasando un nodo padre, pon un nuevo float en alguno de sus campos (de tipo float)
     * ?? funciona si el nodo padre que se pasa como argumento es un subnodo del objeto leido???
     * 
     * @param parentNode parent node
     * @param childFieldName_N1
     * @param increment
     * @return
     */
    public JsonNode incrementChildValueNode_N1_floatType(JsonNode parentNode, String childFieldName_N1, float increment){        
        assert parentNode != null;
        float actualValue = findFirstChildValueNodeByChildNodeFieldName(parentNode, childFieldName_N1).floatValue();
        float newValue = actualValue + increment;                                 
        return objectNodeManager.overrideChildNodeField_N1_floatType(parentNode, childFieldName_N1, newValue);
    }
        
    
    public JsonNode pushNodeInChildArrayNode(JsonNode parentNode, String childArrayNode, JsonNode appendable_Node){
        assert parentNode != null;
        JsonNode arrayNode_nx = findFirstChildObjectNodeByChildNodeFieldName(parentNode, childArrayNode);
        if (arrayNode_nx.isArray()){  
           arrayNode_nx = arrayNodeManager.pushNodeInArrayNode((ArrayNode)arrayNode_nx, appendable_Node);
           return arrayNode_nx;
        }
        return null;
    }
    
    public JsonNode pushElementInArrayNodeN2_insideObjectNodeIndexedNodeN1(JsonNode parentNode,T newElement, String arrayFieldNameN1, int indexObjectNodeInArray, String arrayFieldNameN2){
        assert parentNode != null;
        JsonNode arrayNode_nx = findFirstChildObjectNodeByChildNodeFieldName(parentNode, arrayFieldNameN1);
        if (arrayNode_nx.isArray()){               
            JsonNode nodeIndexed_nx = arrayNodeManager.getNodeInArrayByIndex((ArrayNode)arrayNode_nx, indexObjectNodeInArray);
            // push node in child Node array
            JsonNode appendableNode = parseObjectToNode(newElement);
            pushNodeInChildArrayNode(nodeIndexed_nx, arrayFieldNameN2, appendableNode);             
            return parentNode;
        }
        return null;
    }    

    ////////////////////////////////////////////////
    ///FIND PARENT NODES
    ///////////////////////////////////////////////
    
    public JsonNode findParentNodeByFieldName (JsonNode childNode, String parentFieldName){
        return childNode.findParent(parentFieldName);
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
