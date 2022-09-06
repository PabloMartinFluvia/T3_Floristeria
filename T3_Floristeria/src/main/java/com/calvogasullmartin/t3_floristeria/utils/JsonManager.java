package com.calvogasullmartin.t3_floristeria.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonManager<T> {
    
    private JsonFileManager<T> fileManager;
    private ObjectMapper mapper;
    private Gson gson;    
    private JsonNode mainNode;    
    private JsonNode node;
    private List<JsonNode> listNodes;
    private JsonNode auxiliarNode;

    public JsonManager() {        
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false        
    }
    
    public JsonManager (File archivo) {        
        assert archivo != null;
        assert archivo.isFile();
        assert archivo.exists();
        this.fileManager = new JsonFileManager<>(archivo);
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false  
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
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
    
    public int getIntValue_fromNode(){
        assert node.isInt();
        return node.asInt();
    }
    
    public float getFloatValue_fromNode(){
        assert node.isNumber();
        return node.floatValue();
    }
    
    //SET / REPLACE NODES (sin afectar al main node)
    
    public void setNode_fromMain(){
        node = mainNode;
    }
    
    public void setAuxiliarNode_ObjectInput(T object){           
        auxiliarNode = mapper.convertValue(object, JsonNode.class);        
    }
    
    public void setNode_findFieldByName_fromMain(String childField){
        //System.out.println(mainNode.toPrettyString());
        node = mainNode.get(childField);       
        //System.out.println(node.toPrettyString());
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
        if(!found){
            node = null;
        }
    }    
    
    /**
     * if list node is empty -> puts a new Node in node
     */
    public void replaceNode_fromFirstElementInListNode(){
        assert !listNodes.isEmpty();
        node = listNodes.get(0);        
    }
    
    public void replaceList_NodesListWithChildFloatValue(String childField,float floatValue){  
        Iterator<JsonNode> iterator = listNodes.iterator();
        List<JsonNode> newNodeList = new LinkedList<>();
        JsonNode nodeAux;
        while (iterator.hasNext()){
            nodeAux = iterator.next();
            if(nodeAux.findValue(childField).floatValue() == floatValue){
                newNodeList.add(nodeAux);
            }
        }
        listNodes = newNodeList;
    }
    
    public void replaceList_NodesListWithChildStringValue(String childField,String stringValue){  
        Iterator<JsonNode> iterator = listNodes.iterator();
        List<JsonNode> newNodeList = new LinkedList<>();
        JsonNode nodeAux;
        JsonNode value;
        while (iterator.hasNext()){
            nodeAux = iterator.next();
            value = nodeAux.findValue(childField);
            if(value != null && value.asText().equals(stringValue)){
                newNodeList.add(nodeAux);
            } 
        }
        listNodes = newNodeList;
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
    
    public void updateNode_setNewIntValueInField(String childField,int newValue){
        ((ObjectNode)node).put(childField, newValue);
    }
    
    public void updateNode_incrementFloatValueInField(String childField, float increment){
        float oldValue = node.findValue(childField).floatValue();
        float newValue = oldValue + increment;
        updateNode_setNewFloatValueInField(childField, newValue);
    }
    
    public void updateNode_incrementIntValueInField(String childField, int increment){
        int oldValue = node.findValue(childField).asInt();
        int newValue = oldValue + increment;
        updateNode_setNewIntValueInField(childField, newValue);
    }
    
    public void updateNode_isArray_pushAuxiliarNode(){        
        ((ArrayNode)node).add(auxiliarNode);
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
        T object = gson.fromJson(json, objectClass);        
        return object;        
    }  
    
    public T parseJsonStringToObject(String json, Class<T> objectClass){
        assert json != null;
        T object = gson.fromJson(json, objectClass);        
        return object; 
    }
    
    public String parseObjectToJson(T enitity){
        assert enitity != null;
        return gson.toJson(enitity);
    }
    
    //CHECK
    public boolean isNodeNull(){
        return node == null;
    }
    
    public boolean isListNodesEmpty(){
        return listNodes.isEmpty();
    }
    
    //TEST: para verificar seqüencia de metodos
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
    
    public String testMain(){
        return mainNode.toPrettyString();
    }
}
