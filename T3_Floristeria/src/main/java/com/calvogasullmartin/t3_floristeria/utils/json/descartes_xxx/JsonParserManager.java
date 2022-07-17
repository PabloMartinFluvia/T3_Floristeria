package com.calvogasullmartin.t3_floristeria.utils.json.descartes_xxx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParserManager<T> {
        
    private Gson gson;

    public JsonParserManager() {        
        this.gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false
    }    
    
    ///////////////////////////////////////////////////////////
    // object TO JSON STRING
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
        gson = new GsonBuilder().setPrettyPrinting().create(); //serialize nulls: false   
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
        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create(); //serialize nulls: true   
        return gson.toJson(entidad);        
    }
    
}
