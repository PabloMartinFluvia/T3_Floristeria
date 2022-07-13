package com.calvogasullmartin.t3_floristeria.utils.json;

import com.calvogasullmartin.t3_floristeria.utils.json.plugins.JsonArrayNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.plugins.JsonFileManager;
import com.calvogasullmartin.t3_floristeria.utils.json.plugins.JsonNodeManager;
import com.calvogasullmartin.t3_floristeria.utils.json.plugins.JsonParserManager;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonManager<T> {
    
    private JsonFileManager<T> fileManager;
    private JsonNodeManager<T> nodeManager;
    private JsonParserManager<T> parserManager;
    private JsonArrayNodeManager<T> arrayNodeManager;
    

    public JsonManager() {        
        this.nodeManager = new JsonNodeManager<>();
        this.parserManager = new JsonParserManager<>();
        this.arrayNodeManager = new JsonArrayNodeManager<>();
    }
    
    public JsonManager (File archivo) {
        assert archivo != null;
        assert archivo.isFile();
        assert archivo.exists();
        this.fileManager = new JsonFileManager<>(archivo);
        this.nodeManager = new JsonNodeManager<>();
        this.parserManager = new JsonParserManager<>();
        this.arrayNodeManager = new JsonArrayNodeManager<>();
    }
    
       
    public void writeObjectInFile(T entidad) throws IOException { 
        fileManager.writeObjectInFileJackson(entidad);
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
        JsonNode nodos = fileManager.getNodesFromFile();        
        nodos = nodeManager.incrementTopLevelFloatField(nodos, mainFieldName_typeFloat, increment);                          
        //T objectUpdated = nodeManager.parseNodeToObject(nodos, classInFile);
        //fileManager.writeObjectInFileJackson(objectUpdated);
        fileManager.writeNodeInFileJackson(nodos);
    }   
    
    
    /**
     * Looks for the FIRST field that matches that name in the file (child nodes INCLUDED),
     * and returns the value in String format 
     * (must be valid for parse toSritng -> String, Number, Boolean, NULL).Otherwise (field not found OR is not a field value) -> returns null
     * 
     * @param fieldName
     * @return value in string format 
     * @throws java.io.IOException 
     */
    public String getValueParsedToStringOfFirstFieldMatchesName_fromFile(String fieldName) throws IOException{
        assert fieldName != null;  
        JsonNode node = fileManager.getNodesFromFile();        
        JsonNode fieldNode = nodeManager.findFirstNodeValueFieldThatMatchesFieldName(node, fieldName);            
        if (fieldNode != null && fieldNode.isValueNode()){ //node not NULL && value: String, Numbre, Boolean, NULL
            return fieldNode.asText();
        }else {
            return null;
        }        
    }
    
    /**
     * Looks for the FIRST field that matches that name in the file (child nodes INCLUDED),
     * and returns the value in Float format (must be posible to parse to float).Otherwise (field not found) -> returns null
     * 
     * @param fieldName
     * @return value in string format 
     * @throws java.io.IOException 
     */
    public Float getValueParsedToFloatOfFirstFieldMatchesName_fromFile(String fieldName) throws IOException {
        assert fieldName != null;        
        JsonNode node = fileManager.getNodesFromFile();            
        JsonNode fieldNode = nodeManager.findFirstNodeValueFieldThatMatchesFieldName(node, fieldName);         
        if (fieldNode != null && fieldNode.isNumber()){ //node not NULL && value is numberic                        
            return fieldNode.floatValue();
        }else {
            return null;
        }        
    }
    
    public int findMaxIntInMultipleFieldsWithSameName(String nombreAtributoId) throws IOException{
        JsonNode mainNode = fileManager.getNodesFromFile();
        List<JsonNode> nodos = nodeManager.finAllNodes(mainNode, nombreAtributoId);
        int maxInt = 0;
        for(JsonNode nodo: nodos){
            if(nodo.isInt() && nodo.asInt()> maxInt){
                maxInt = nodo.asInt();
            }
        }
        return maxInt;
    }
    
    public void addObjectInUniqueArray(T newElement, String arrayFieldName) throws IOException{
        JsonNode mainNode = fileManager.getNodesFromFile();
        JsonNode arrayNode = nodeManager.findFirstNodeFieldThatMatchesFieldName(mainNode, arrayFieldName);
        JsonNode newNode = nodeManager.parseObjectToNode(newElement);
        arrayNodeManager.pushNodeInArrayNode(arrayNode, newNode);
        fileManager.writeNodeInFileJackson(mainNode);
    }
    
    public T getObjectInUniqueArrayInFileByIndex(String uniqueArrayFieldName,Class<T> objectClass, int index) throws IOException{
        JsonNode mainNode = fileManager.getNodesFromFile();
        JsonNode arrayNode = nodeManager.findFirstNodeFieldThatMatchesFieldName(mainNode, uniqueArrayFieldName);
        JsonNode nodeTarget = arrayNodeManager.getNodeInArrayByIndex(arrayNode, index);
        T objectTarget = nodeManager.parseNodeToObject(nodeTarget, objectClass);
        return objectTarget;
    }
    
    public T[] getUniqueArrayInFileParsedToArrayObject(String uniqueArrayFieldName,Class<T> arrayObjecClass) throws IOException{
        JsonNode mainNode = fileManager.getNodesFromFile();
        JsonNode arrayNode = nodeManager.findFirstNodeFieldThatMatchesFieldName(mainNode, uniqueArrayFieldName);
        T[] arrayTarget = (T[]) arrayNodeManager.parseNodeArrayToObjectArray(arrayNode, arrayObjecClass);        
        return arrayTarget;
    }
    
    public void goToEspecificObjectInArrayInFileAndAddObjectToNestedArrayAndSave(T newElement, String fieldArray, int arrayIndex, String fieldNestedArray, Class<T> classInFile) throws IOException {        
        JsonNode mainNode = fileManager.getNodesFromFile();        
        mainNode = nodeManager.goToEspecificObjectInArrayAndAddObjectToNestedArray(mainNode,newElement, fieldArray, arrayIndex, fieldNestedArray);        
        // T objectUpdated = nodeManager.parseNodeToObject(mainNode, classInFile);
        //fileManager.writeObjectInFileJackson(objectUpdated);
        fileManager.writeNodeInFileJackson(mainNode);
    }                      
}
