package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    
    public final static String DB_NAME = "floristeria";
    
    private static ConexionMySQL conexionMySQL = null;
    
    private static String pathGeneralConexion;        
    
    private static String user;
    
    private static String password;
    
    private ConexionMySQL(){        
    }
    
    public static ConexionMySQL getInstance(){
        if(conexionMySQL == null){
            conexionMySQL = new ConexionMySQL();
        }
        return conexionMySQL;
    }
    
    public Connection getGeneralConexion() throws SQLException{
        return DriverManager.getConnection(pathGeneralConexion,user,password);
    }
    
    public Connection getBDConexion() throws SQLException{
        return DriverManager.getConnection(pathGeneralConexion+DB_NAME,user,password);
    }
    
    public static void setConexionValues(String[] argsConexion){
        assert conexionMySQL == null;
        assert argsConexion.length == 3;
        pathGeneralConexion = "jdbc:mysql://localhost:"+argsConexion[0]+"/";//no db name
        user = argsConexion[1];
        password = argsConexion[2];
    }
}
