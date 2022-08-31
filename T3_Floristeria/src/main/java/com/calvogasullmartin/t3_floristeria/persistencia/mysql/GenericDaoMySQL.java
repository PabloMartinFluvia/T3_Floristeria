package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.persistencia.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenericDaoMySQL<T, ID> implements GenericDao<T, ID> {
    
    protected ConexionMySQL conexionMySQL;
    
    protected Connection conexion;
    
    protected String query;
    
    protected PreparedStatement preparedStatement;
    
    protected Statement statement;
    
    protected ResultSet result;

    public GenericDaoMySQL() {
        this.conexionMySQL = ConexionMySQL.getInstance();
    }
        
}
