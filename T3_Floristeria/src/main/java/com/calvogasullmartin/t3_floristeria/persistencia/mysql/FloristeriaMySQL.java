package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.modelos.Floristeria;
import com.calvogasullmartin.t3_floristeria.persistencia.FloristeriaDao;
import java.sql.SQLException;

public class FloristeriaMySQL extends GenericDaoMySQL<Floristeria, Integer> implements FloristeriaDao {

    @Override
    public void create(Floristeria floristeria) throws SQLException {        
        try {
            conexion = conexionMySQL.getBDConexion();
            conexion.setAutoCommit(false);
            query = "INSERT INTO floristerias (nombre_floristeria) VALUES (?)";
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, floristeria.getNombre_floristeria());
            preparedStatement.executeUpdate();
            query = "INSERT INTO conjuntos (floristeria_id) VALUES (1)";
            statement = conexion.createStatement();            
            for (int i =0; i < floristeria.getStocks().length; i++){
                statement.executeUpdate(query);
            }                                
            conexion.commit();
            preparedStatement.close();
            statement.close();            
            conexion.close();
        } catch (SQLException exception) {
            if(conexion != null){
                conexion.rollback();
            }
            throw exception;
        }
    }

    @Override
    public String getName() throws SQLException {
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement();
        query = "SELECT nombre_floristeria FROM floristerias WHERE floristeria_id = 1";
        result = statement.executeQuery(query);
        String name = null;
        if(result.next()){
            name = result.getString("nombre_floristeria");
        }else{
            throw new SQLException("Error. Floristeria badly inizialized");
        }
        statement.close();
        result.close();
        conexion.close();
        return name;
    }

    @Override
    public float getValorFloristeria() throws SQLException {   
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement();
        query = "SELECT valorStocksTotal FROM floristerias WHERE floristeria_id = 1";        
        result = statement.executeQuery(query);
        float valor = 0.0f;
        if(result.next()){
            valor = result.getFloat("valorStocksTotal"); 
        }
        statement.close();
        result.close();
        conexion.close();
        return valor;        
    }

    @Override
    public float getFacturacionFloristeria() throws SQLException {
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement();
        query = "SELECT valorTicketsTotal FROM floristerias WHERE floristeria_id = 1";        
        result = statement.executeQuery(query);
        float valor = 0.0f;
        if(result.next()){
            valor = result.getFloat("valorTicketsTotal"); 
        }
        statement.close();
        result.close();
        conexion.close();
        return valor; 
    }

    @Override
    public void incrementarValorFloristeria(float incrementoValor) throws SQLException {
        conexion = conexionMySQL.getBDConexion();
        query = "UPDATE floristerias SET valorStocksTotal = valorStocksTotal+? WHERE floristeria_id = 1";
        preparedStatement = conexion.prepareStatement(query);    
        preparedStatement.setFloat(1, incrementoValor);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conexion.close();
    }

    @Override
    public void incrementarFacturacionFloristeria(float incrementoValor) throws SQLException {
        conexion = conexionMySQL.getBDConexion();
        query = "UPDATE floristerias SET valorTicketsTotal = valorTicketsTotal+? WHERE floristeria_id = 1";
        preparedStatement = conexion.prepareStatement(query);    
        preparedStatement.setFloat(1, incrementoValor);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conexion.close();
    }
}
