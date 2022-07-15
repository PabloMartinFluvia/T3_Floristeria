package com.calvogasullmartin.t3_floristeria.utils;

import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xx.JsonArrayNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xx.JsonFileManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xx.JsonNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xx.JsonObjectManager;
import com.calvogasullmartin.t3_floristeria.utils.json.descartes_xx.JsonParserManager;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class JsonManager<T> {
    
    private JsonFileManager<T> fileManager;
    private ObjectMapper mapper;
    private Gson gson;    
    private JsonNode mainNode;    
    private JsonNode auxiliarNode;
    private List<JsonNode> listAuxiliarNodes;
    private ArrayNode auxiliarArrayNode; 
    
    
    
    
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
        auxiliarNode = null; 
        listAuxiliarNodes = null;
        auxiliarArrayNode = null;
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
    
    public String getAuxiliarNode_asText(){
        assert auxiliarNode.isTextual();
        return auxiliarNode.asText();
    }
    
    public int getListAuxiliarNodes_MaxIntValue(){       
        int maxInt = 0;
        for(JsonNode node: listAuxiliarNodes){
            int intValue = node.asInt();
            if(intValue> maxInt){
                maxInt =intValue;
            }
        }
        return maxInt;
    }
    
     public T[] getAuxiliarArray_asObjectArray (Class<T> arrayClass){   
        T[] arrayTarget = (T[])parseNodeArrayToObjectArray(auxiliarArrayNode, arrayClass);         
        return arrayTarget;
    }
    
    //SET / REPLACE NODES (sin afectar al main node)
    
    public void setAuxiliarNode_mainNode(){
        auxiliarNode = mainNode;
    }
    
    public void setAuxiliarNode_ObjectInput(T object){
        auxiliarNode = mapper.valueToTree(object);        
    }
    
    public void setAuxiliarNode_findFieldByName(String childField){
        auxiliarNode = mainNode.get(childField);        
    }        
    
    public void setListAuxiliarNodes_findAllFieldsByName(String childField){        
        listAuxiliarNodes = auxiliarNode.findValues(childField);
    }
    
    public void setArrayAuxiliarNode_fromAuxiliarNode(){
        auxiliarArrayNode = (ArrayNode)auxiliarNode;  
        System.out.println(auxiliarArrayNode == null);
        System.out.println(auxiliarArrayNode.isNull());
        System.out.println(auxiliarArrayNode.isArray());
        System.out.println(auxiliarArrayNode.isEmpty());        
        System.out.println(auxiliarNode.toPrettyString()+"\n\n");
    }
    
    public void replaceAuxiliarNode_nodeInAuxiliarArrayNodeByIndex(int index){        
        auxiliarNode = auxiliarArrayNode.get(index);        
    }
    
    public void replaceAuxiliarNode_findFieldByName(String childField){          
        auxiliarNode = auxiliarNode.get(childField);        
    }
    
    public boolean replaceAuxliarNode_nodeInArrayWithChildIntValue(String childField,int intValue){
        JsonNode node = auxiliarNode;
        assert node.isArray();        
        Iterator <JsonNode> iterador = node.iterator();
        boolean found = false;
        JsonNode nodeTarget;
        while (!found && iterador.hasNext()){
            nodeTarget = iterador.next();
            if(nodeTarget.findValue(childField).asInt() == intValue){
                found = true;
                auxiliarNode = nodeTarget;
            }
        }
        return found;   
    }    

    //CHECK NODES (booleans)
    
    public boolean inAuxiliarNodes_hasAnyIntValue(int intValue){
        List<JsonNode> nodeList = listAuxiliarNodes;               
        Iterator <JsonNode> iterador = nodeList.iterator();
        boolean found = false;
        JsonNode node;
        while (!found && iterador.hasNext()){
            node = iterador.next();
            assert node.isInt();
            if(node.asInt() == intValue){
                found = true;                
            }
        }
        return found;
    }
    
    public boolean isAuxiliarArrayNode_noLength(){
        
    }
    
    
    //UPADATE NODES (afectando al main node)   
    
    public void upadteAuxiliarNode_setNewFloatValueInField(String childField,float newValue){
        ((ObjectNode)auxiliarNode).put(childField, newValue);
    }
    
    public void upadteAuxiliarNode_incrementFloatValueInField(String childField, float increment){
        float oldValue = auxiliarNode.findValue(childField).floatValue();
        float newValue = oldValue + increment;
        upadteAuxiliarNode_setNewFloatValueInField(childField, newValue);
    }
    
    public void updateAuxiliarArray_pushAuxiliarNode(){
        if(auxiliarArrayNode.size() == 0){
            auxiliarArrayNode.insert(0, auxiliarNode);
        }else{
            auxiliarArrayNode.add(auxiliarNode);    
        }                             
    }
    
    
    //DELETE NODES (afectando al main node)
    
    public void updateArrayAuxiliarNode_deleteFirstElementByCildIntValue(String childField,int intValue){
        Iterator <JsonNode> iterador = auxiliarArrayNode.iterator();
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
    
    public T parseNodeArrayToObjectArray(ArrayNode arrayNode, Class<T> arrayClass){  //NombreClasse[].class
        assert arrayNode != null;
        String json = arrayNode.toPrettyString();
        T arrayObject = gson.fromJson(json, arrayClass);        
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
