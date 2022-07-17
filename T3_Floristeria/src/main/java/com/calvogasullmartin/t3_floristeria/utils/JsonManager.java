package com.calvogasullmartin.t3_floristeria.utils;

import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx.JsonArrayNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx.JsonFileManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx.JsonNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx.JsonObjectManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx.JsonParserManager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonManager<T> {
    
    private JsonFileManager<T> fileManager;
    private ObjectMapper mapper;
    private Gson gson;    
    private JsonNode mainNode;    
    private JsonNode node;
    private List<JsonNode> listNodes;
    private JsonNode auxiliarNode;
    
    
    
    
    private JsonNode childNode_x;
             
    private JsonNode parentNode_x;    
    private JsonNode parentNode;
    private JsonNode targetNode;
    
    private JsonNodeManager<T> nodeManager;
    private JsonParserManager<T> parserManager;
    private JsonArrayNodeManager<T> arrayNodeManager;
    private final JsonObjectManager<T> objectNodeManager;
    

    public JsonManager() {        
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
        
        
        this.nodeManager = new JsonNodeManager<>();
        this.parserManager = new JsonParserManager<>();
        this.arrayNodeManager = new JsonArrayNodeManager<>();
        this.objectNodeManager = new JsonObjectManager<>();
    }
    
    public JsonManager (File archivo) {
        assert archivo != null;
        assert archivo.isFile();
        assert archivo.exists();
        this.fileManager = new JsonFileManager<>(archivo);
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
        
        
        this.nodeManager = new JsonNodeManager<>();
        this.parserManager = new JsonParserManager<>();
        this.arrayNodeManager = new JsonArrayNodeManager<>();
        this.objectNodeManager = new JsonObjectManager<>();
    }
    
     
    //INITIAL - END
    
    public void setAuxiliarNodesNull(){        
        node = null; 
        listNodes = null;
        auxiliarNode = null;
    }
    
     public void setMainNode_FromFile() throws IOException { 
        mainNode = fileManager.getNodesFromFile();        
    }
         
    public void writeObjectInFile(T object) throws IOException { 
        fileManager.writeObjectInFileJackson(object);
    }        
           
    public void saveMainNodeInFile() throws IOException { 
        fileManager.writeNodeInFileJackson(mainNode);
    }              
    
    
    //FIND (get) VALUES
    
    public String getStringValue_fromNode(){
        assert node.isTextual();
        return node.asText();
    }
    
    public int getMaxIntValue_fromListNodes(){       
        int maxInt = 0;
        for(JsonNode node: listNodes){
            int intValue = node.asInt();
            if(intValue> maxInt){
                maxInt =intValue;
            }
        }
        return maxInt;
    }         
    
    //SET / REPLACE NODES (sin afectar al main node)
    
    public void setNode_fromMain(){
        node = mainNode;
    }
    
    public void setAuxiliarNode_ObjectInput(T object){           
        auxiliarNode = mapper.convertValue(object, JsonNode.class);        
    }
    
    public void setNode_findFieldByName_fromMain(String childField){
        node = mainNode.get(childField);        
    }        
    
    public void setListNodes_findAllFieldsByName(String childField){        
        listNodes = node.findValues(childField);
    }    
    
    public void replaceNode_isArray_nodeByIndex(int index){        
        assert node.isArray();
        node = node.get(index);           
    }
    
    public void replaceNode_findFieldByName(String childField){          
        node = node.get(childField);                         
    }    
    
    public void replaceNode_isArray_NodeIndexedWithChildIntValue(String childField,int intValue){        
        assert node.isArray();        
        Iterator <JsonNode> iterador = node.iterator();
        boolean found = false;
        JsonNode nodeTarget;
        while (!found && iterador.hasNext()){
            nodeTarget = iterador.next();
            if(nodeTarget.findValue(childField).asInt() == intValue){
                found = true;
                node = nodeTarget;
            }
        }  
    }    

    //CHECK NODES (booleans)
    
    public boolean inListNodes_hasAnyIntValue(int intValue){
        List<JsonNode> nodeList = listNodes;               
        Iterator <JsonNode> iterador = nodeList.iterator();
        boolean found = false;
        JsonNode listedNode;
        while (!found && iterador.hasNext()){
            listedNode = iterador.next();
            assert listedNode.isInt();
            if(listedNode.asInt() == intValue){
                found = true;                
            }
        }
        return found;
    }
    
   
    
    
    //UPADATE NODES (afectando al main node)   
    
    public void updateNode_setNewFloatValueInField(String childField,float newValue){
        ((ObjectNode)node).put(childField, newValue);
    }
    
    public void updateNode_incrementFloatValueInField(String childField, float increment){
        float oldValue = node.findValue(childField).floatValue();
        float newValue = oldValue + increment;
        updateNode_setNewFloatValueInField(childField, newValue);
    }
    
    public void updateNode_isArray_pushAuxiliarNode(){        
        ((ArrayNode)node).add(auxiliarNode);
    }
    
    
    public String test(){
        return node.toPrettyString();
    }
    
    public String testList(){
        String test = "";
        Iterator<JsonNode> iterador = listNodes.iterator();
        while (iterador.hasNext()){
            JsonNode nodeListed = iterador.next();
            test = test + "\n\n" + nodeListed.toPrettyString();
        }
        
        return test;
    }
    
    //DELETE NODES (afectando al main node)
    
    public void updateNode_isArray_deleteFirstElementByChildIntValue(String childField,int intValue){
        assert node.isArray();
        Iterator <JsonNode> iterador = node.iterator();
        boolean found = false;
        JsonNode nodeToDelete;
        while (!found && iterador.hasNext()){
            nodeToDelete = iterador.next();            
            if(nodeToDelete.findValue(childField).asInt() == intValue){
                iterador.remove();
            }
        }
    }
    
    
    //// PARSE
    /**
     * si vui que em retorni un MiClasse[] -> dir que la classe es MiClase[].class
     * @param objectClass
     * @return 
     */
    public T parseNodeToObject(Class<T> objectClass){  
        assert node != null;
        String json = node.toPrettyString();
        T arrayObject = gson.fromJson(json, objectClass);        
        return arrayObject;        
    }
    
    
    
    
    
    
    
    
    
    public T parseChildNode_x_toObject(Class<T> classOfObject){  
        return nodeManager.parseNodeToObject(childNode_x, classOfObject);
    }
   
    /**
     * Informando de la clase que hay en el archivo, y el atributo de tipo float que se desea midificar:
     * Se le pasa el nuevo valor y lo guarda de nuevo en el archivo.??pendiente: como hacerlo cuando el atributo no esta en el "nivel principal" (sino que está emebebd)
     * 
     * @param mainFieldName_typeFloat
     * @param increment
     * @param classInFile 
     * @throws java.io.IOException 
     */    
    public void incrementMainFloatFieldInFile(String mainFieldName_typeFloat, float increment, Class<T> classInFile) throws IOException{        
        setMainNode_FromFile();
        mainNode = nodeManager.incrementChildValueNode_N1_floatType(mainNode, mainFieldName_typeFloat, increment);                                  
        saveMainNodeInFile();
    }   
    
    public void incrementFloatFieldOfObjectIndexedInArrayAndSaveInFile
        (float increment,String fieldToUpdate, int array_inex,String fieldArray) throws IOException{
        setMainNode_FromFile();
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, fieldArray);
        JsonNode nodeInIarray = arrayNodeManager.getNodeInArrayByIndex((ArrayNode)arrayNode, array_inex);
        nodeManager.incrementChildValueNode_N1_floatType(nodeInIarray, fieldToUpdate, increment);
        saveMainNodeInFile();
    }            
    
    /**
     * Looks for the FIRST chield field that matches that name (parent is the main node)
     * and returns the value in String format 
     * (must be valid for parse toSritng -> String, Number, Boolean, NULL).Otherwise (field not found OR is not a field value) -> returns null
     * 
     * @param childFieldName
     * @return value in string format 
     * @throws java.io.IOException 
     */
    public String getValueParsedToStingOfFirstChildNode_x(String childFieldName) throws IOException{
        assert childFieldName != null;        
        JsonNode childNode = nodeManager.findFirstChildValueNodeByChildNodeFieldName(mainNode, childFieldName);            
        return nodeManager.getStringValueOfNode(childNode);
    }
    
    /**
     * Looks for the FIRST chield field that matches that name (parent is the main node)
     * and returns the value in Float format (must be posible to parse to float).Otherwise (field not found) -> returns null
     * 
     * @param childFieldName
     * @return value in string format 
     * @throws java.io.IOException 
     */
    public Float getValueParsedToFloatOfFirstChildNode_x(String childFieldName) throws IOException {
        assert childFieldName != null;                          
        JsonNode childNode = nodeManager.findFirstChildValueNodeByChildNodeFieldName(mainNode, childFieldName);         
       return nodeManager.getFloatValueOfNode(childNode);
    }
    
    /**
     * Looks for all child Nodes with that name (parent node is main node) ang returns
     * max value.
     * Values of the child nodes MUST be int type
     * @param childFieldName_intType
     * @return
     * @throws IOException 
     */
    public int findMaxIntValueInMultipleChildNodes(String childFieldName_intType) throws IOException{        
        List<JsonNode> childNodesList = nodeManager.finAllChildNodesByChildNodeFieldName(mainNode, childFieldName_intType);
        int maxInt = 0;
        for(JsonNode childNode: childNodesList){
            int intValue = nodeManager.getIntValueOfNode(childNode);
            if(intValue> maxInt){
                maxInt =intValue;
            }
        }
        return maxInt;
    }
    
    public T findInFirstArrayNodeFirstObjectByChildIIntNodeValueField (String arrayFieldName, int intValue, String intFieldName, String objectField, Class<T> classObjectTarget) throws IOException{        
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, arrayFieldName);
        JsonNode childNode = nodeManager.findFirstChildNodeWithSpecificIntValue(arrayNode, intFieldName, intValue);
        if (childNode.isNull()){
            return null;
        }else{
            JsonNode objectNode = nodeManager.findParentNodeByFieldName(childNode, objectField);
            if (objectNode != null){
                T objectTarget = nodeManager.parseNodeToObject(objectNode, classObjectTarget);
                return objectTarget;
            } else{
                return null;
            }                     
        }            
    }
    
    public boolean updateChildNode_x_IterateArrayNodeTillChildNodeHasIntValue(int intValue, String intFieldName) throws IOException{        
        Iterator <JsonNode> iterador = childNode_x.iterator();
        boolean found = false;
        JsonNode nodeTarget;
        while (!found && iterador.hasNext()){
            nodeTarget = iterador.next();
            if(nodeTarget.findValue(intFieldName).asInt() == intValue){
                found = true;
                childNode_x = nodeTarget;
            }
        }
        return found;          
    }
    
    /**
     * Find first arrayNode (in childrens) and push de element (parent is mainNode)
     * @param newElement
     * @param arrayFieldName
     * @throws IOException 
     */
    public void addObjectInUniqueArray(T newElement, String arrayFieldName) throws IOException{        
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, arrayFieldName);
        JsonNode newNode = nodeManager.parseObjectToNode(newElement);
        arrayNodeManager.pushNodeInArrayNode((ArrayNode)arrayNode, newNode);        
    }
    
    /**
     * Get first arrayNode found with that name + parse it to Object[]
     * @param arrayFieldName
     * @param arrayObjecClass
     * @return
     * @throws IOException 
     */
    public T[] getObjectArray(String arrayFieldName,Class<T> arrayObjecClass) throws IOException{        
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, arrayFieldName);
        T[] arrayTarget = (T[]) arrayNodeManager.parseNodeArrayToObjectArray((ArrayNode)arrayNode, arrayObjecClass);        
        return arrayTarget;
    }
    
    /**
     * get the node indexed in the first arrayNode found with that name + parse it to Object.
     * Parent is main node
     * @param arrayFieldName
     * @param objectClass
     * @param index
     * @return
     * @throws IOException 
     */
    public T getObjectIndexedInArray(String arrayFieldName,Class<T> objectClass, int index) throws IOException{        
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, arrayFieldName);
        JsonNode nodeTarget = arrayNodeManager.getNodeInArrayByIndex((ArrayNode)arrayNode, index);
        T objectTarget = nodeManager.parseNodeToObject(nodeTarget, objectClass);
        return objectTarget;
    }            

    /**
     * Looks for the array of parents + get the parent by Id + get the node and save it in childNode_x
     * 
     * @param parentsArrayFieldName
     * @param parent_index_in_arrayTop
     * @param arrayNestedFieldName 
     */
    public void setInChildNode_x_getChildNode_ParentIsIndexedInArray(String parentsArrayFieldName, int parent_index_in_arrayTop, String arrayNestedFieldName){        
        JsonNode arrayNode = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(mainNode, parentsArrayFieldName);
        JsonNode nodeTarget = arrayNodeManager.getNodeInArrayByIndex((ArrayNode)arrayNode, parent_index_in_arrayTop);
        childNode_x = nodeManager.findFirstChildObjectNodeByChildNodeFieldName(nodeTarget, arrayNestedFieldName);
    }
    
    /**
     * gets the value in childNode_x atribute and parse it to the class of the array (MyClass[].class)
     * 
     * @param arrayClass
     * @return 
     */
    public T[] getFromChildNode_x_parseNodeArrayToObjectArray (Class<T> arrayClass){
        T[] arrayTarget = (T[]) arrayNodeManager.parseNodeArrayToObjectArray((ArrayNode)childNode_x, arrayClass);        
        return arrayTarget;
    }
            
    public void goToEspecificObjectInArrayInFileAndAddObjectToNestedArrayAndSave(T newElement, String fieldArray, int arrayIndex, String fieldNestedArray, Class<T> classInFile) throws IOException {                
        nodeManager.goToEspecificObjectInArrayAndAddObjectToNestedArray(mainNode,newElement, fieldArray, arrayIndex, fieldNestedArray);                
    }  
    
}
