package com.calvogasullmartin.t3_floristeria.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonManager<T, ID> {
    
    private File archivo;
    private Gson gson;
    private ObjectMapper mapper;

    public JsonManager() {
        archivo = null; 
        this.gson = new Gson();
        this.mapper = new ObjectMapper(); 
    }
    
    public JsonManager(File archivo){
        assert archivo != null;
        assert archivo.isFile();
        assert archivo.exists();
        
        this.archivo = archivo;
        this.gson = new Gson();
        this.mapper = new ObjectMapper(); 
    }
    
    //////////////////////////////////////////////////
    // OJECT TO FILE SERIALIZED IN JSON 
    ///////////////////////////////////////////////
    
    /**
     * write object (seialized in json Format) in the File of the Class.
     * Writer has appendable = true. 
     * (But it would be better one single document with collections / arrays).
     * NULL fields are ignored.
     * @param entidad
     * @throws IOException 
     */
    public void writeInFile(T entidad) throws IOException{ 
        assert entidad != null;
        
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,true)); //apendable: true
        gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false       
        gson.toJson(entidad,escritor); // usar algun writer que implementi Appendable interface
        escritor.close();
    }
    
    ///////////////////////////////////////////////////////////
    // SERIALIZE TO JSON STRING
    ////////////////////////////////////////////////////////
    
    /**
     * objtect to String in json format. 
     * NULL fields are ignored
     * 
     * @param entidad
     * @return String in Json format
     */
    public String ToJsonString (T entidad){   
        assert entidad != null;        
        gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(entidad);        
    }
    
    /**
     * objtect to String in json format. 
     * NULL fields are inluded
     * 
     * @param entidad
     * @return String in Json format
     */
    public String ToJsonStringIncludeNulls (T entidad){
        assert entidad != null;        
        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        return gson.toJson(entidad);        
    }
    
    
    //////////////////////////////////////////////////////////////////////
    //READ FILE WRITED IN JSON
    //////////////////////////////////////////////////////////////////
    /**
     * Looks for the FIRST field that matches that name in the file (child nodes INCLUDED),
     * and returns the value in String format 
     * (must be valid for parse toSritng -> String, Number, Boolean, NULL).
     * Otherwise (field not found OR is not a field value) -> returns null
     * 
     * @param nieldName
     * @return value in string format
     * @throws IOException 
     */
    public String getValueToString_fromFile(String nieldName) throws IOException{
        assert nieldName != null;        
        JsonNode node = loadTreeNodesFromFile().findValue(nieldName);
        if (node != null && node.isValueNode()){ //node not NULL && value: String, Numbre, Boolean, NULL
            return node.asText();
        }else {
            return null;
        }        
    }
    
    /**
     * Read the file and returns the node estructure
     * @return
     * @throws IOException 
     */
    public JsonNode loadTreeNodesFromFile() throws IOException{
        JsonNode nodos = mapper.readTree(archivo);
        return nodos;
    }
    
    /**
     * Checks the file, and returns a sorted list with the names of the main
     * nodes / top level fields.
     * 
     * @return
     * @throws IOException 
     */
    public List<String> getMainFieldsNamesFromFile() throws IOException{
        JsonNode nodos = loadTreeNodesFromFile();
        Iterator<String> fields = nodos.fieldNames();// only the top level fields
        List<String> listaFieldsNames = new LinkedList<>();
        while (fields.hasNext()){
            listaFieldsNames.add(fields.next());
        }
        return listaFieldsNames;
    }
    
    
    
    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }        
}
