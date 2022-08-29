package com.calvogasullmartin.t3_floristeria.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileManager<T> {

    private File archivo;
    private ObjectMapper mapper;
    private BufferedWriter escritor;
    private Gson gson;

    public JsonFileManager(File archivo) {
        assert archivo != null;
        assert archivo.isFile();
        assert archivo.exists();

        this.archivo = archivo;
        this.mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT); // serialize nulls: false   
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
    }

    /**
     * write object (seialized in json Format) in the File of the Class. Writer
     * has appendable = false. NULL fields are ignored.
     *
     * @param entidad
     * @throws IOException
     */
    public void writeObjectInFileJackson(T entidad) throws IOException {                
            this.escritor = new BufferedWriter(new FileWriter(archivo, false)); //apendable: false  
            assert entidad != null;
            mapper.writeValue(escritor, entidad);
            escritor.close();                
    }

    /**
     * write object (seialized in json Format) in the File of the Class.Writer
 has appendable = false. NULL fields are ignored.
     *
     * @param entidad
     * @throws java.io.IOException
     */
    public void writeObjectInFileGson(T entidad) throws IOException {        
            this.escritor = new BufferedWriter(new FileWriter(archivo, false)); //apendable: false  
            assert entidad != null;
            gson.toJson(entidad, escritor); // usar algun writer que implementi Appendable interface
            escritor.close();

    }

    /**
     * Read the file and returns the node estructure
     *
     * @return
     * @throws IOException
     */
    public JsonNode getNodesFromFile() throws IOException  {        
        return mapper.readTree(archivo);
    }
    
    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * NO OK DONA EROR
     * @param mainNode
     * @throws IOException 
     */
    public void writeNodeInFileGson(JsonNode mainNode) throws IOException{
        this.escritor = new BufferedWriter(new FileWriter(archivo, false)); //apendable: false  
        gson.toJson(mainNode, escritor);
        escritor.close();
    }
    
    public void writeNodeInFileJackson(JsonNode mainNode) throws IOException{
        this.escritor = new BufferedWriter(new FileWriter(archivo, false)); //apendable: false  
        mapper.writeValue(archivo, mainNode);
        escritor.close();
    }
}
