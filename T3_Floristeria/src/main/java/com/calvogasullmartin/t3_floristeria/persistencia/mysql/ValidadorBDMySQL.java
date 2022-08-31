package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.persistencia.ValidadorBD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class ValidadorBDMySQL implements ValidadorBD{
    
    private ConexionMySQL conexionMySQL;

    public ValidadorBDMySQL() {
        this.conexionMySQL = ConexionMySQL.getInstance();
    }
    
    @Override
    public boolean isBDInicizialized() throws SQLException {
        Connection conexion = conexionMySQL.getGeneralConexion();
        ResultSet result = conexion.getMetaData().getCatalogs();
        boolean found = false;
        while (!found && result.next()){
            if(result.getString("TABLE_CAT").equals(ConexionMySQL.DB_NAME)){
                found = true;
            }
        }        
        result.close();
        conexion.close();
        return found;
    }

    @Override
    public void initBD() throws IOException, SQLException {        
        String scripPath = 
                "src"+File.separator+"docs"+File.separator+"initMySqlBD.sql";                
        BufferedReader reader = new BufferedReader(new FileReader(scripPath));
        Connection conexion = conexionMySQL.getGeneralConexion();
        ScriptRunner scriptExecutor = new ScriptRunner(conexion);      
        scriptExecutor.setLogWriter(null);
        scriptExecutor.runScript(reader);
        reader.close();
        conexion.close();
    }    
}
