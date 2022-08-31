package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ConjuntoProductos;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ConjuntoProductosDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConjuntoProductosMySQL extends GenericDaoMySQL<ConjuntoProductos, Integer> implements ConjuntoProductosDao {

    @Override
    public void createTiquet(ConjuntoProductos tiquet) throws SQLException {
        assert tiquet != null;
        assert tiquet.getValor_Productos()>0;
        assert !tiquet.getProductos().isEmpty();
        try {
            conexion = conexionMySQL.getBDConexion();
            conexion.setAutoCommit(false);
            insertTicket(tiquet.getValor_Productos());
            associateProducts(tiquet);
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw ex;
        }
    }

    private void insertTicket(float value) throws SQLException {
        query = "INSERT INTO conjuntos (valor_Productos, floristeria_id) VALUES (?, 1)";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setFloat(1, value);
        preparedStatement.executeUpdate();
    }

    private void associateProducts(ConjuntoProductos tiquet) throws SQLException {
        int tiquet_id = getLastTicketId();
        query = "INSERT INTO conjuntos_has_products (conjunto_id, producto_id, cantidad) VALUES (?,?,?)";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, tiquet_id);
        for (ProductoUnidad productoUnidad : tiquet.getProductos()) {
            preparedStatement.setInt(2, productoUnidad.idProducto());
            preparedStatement.setInt(3, productoUnidad.getCantidad());
            preparedStatement.executeUpdate();
        }
    }

    private int getLastTicketId() throws SQLException {
        query = "SELECT conjunto_id FROM conjuntos ORDER BY conjunto_id DESC LIMIT 1";
        statement = conexion.createStatement();
        result = statement.executeQuery(query);
        int id = 0;
        if (result.next()) {
            id = result.getInt(1);
        } 
        if (id > Categoria.values().length){
            return id;
        }else {
            throw new SQLException("Error. Ticket has not been inserted corretly.");
        }
    }

    @Override
    public ConjuntoProductos getOneStockById(int idStock) throws SQLException {
        assert idStock >= 1 && idStock <= Categoria.values().length;
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        query = "SELECT c.conjunto_id, c.valor_Productos, cp.cantidad, p.producto_id,  p.categoria, "
                + "p.nombre, p.precio, p.altura, p.color, p.material "
                + "FROM conjuntos AS c "
                + "LEFT JOIN conjuntos_has_products AS cp  ON c.conjunto_id = cp.conjunto_id "
                + "LEFT JOIN productos AS p ON cp.producto_id = p.producto_id "
                + "WHERE c.conjunto_id = "+idStock+" "
                + "ORDER BY p.producto_id ASC";
        result = statement.executeQuery(query);
        ConjuntoProductos stock = null;
        if(result.next()){
            stock = generateConjunto(result);
        }
        statement.close();
        result.close();
        conexion.close();
        return stock;
    }
    
    private ConjuntoProductos generateConjunto(ResultSet result) throws SQLException {
        ConjuntoProductos conjunto = new ConjuntoProductos();
        conjunto.setConjunto_id(result.getInt("conjunto_id"));
        conjunto.setValor_Productos(result.getFloat("valor_Productos"));
        ProductoUnidad productoUnidad;
        boolean conjuntoCompleted = false;
        do{
            productoUnidad = new ProductoUnidad();            
            if(result.getString("categoria") != null){
                productoUnidad.setCantidad(result.getInt("cantidad"));
                productoUnidad.setProducto(new ProductoCompletoMySQL().generateProduct(result));
                conjunto.getProductos().add(productoUnidad);
            }                        
            if(result.next()){
                if(result.getInt("conjunto_id") != conjunto.getConjunto_id()){
                    conjuntoCompleted = true;
                    result.previous();
                }
            }else{
                conjuntoCompleted = true;
            }            
        } while(!conjuntoCompleted);
        return conjunto;
    }

    @Override
    public List<ConjuntoProductos> getAllStocks() throws SQLException {       
        query = "SELECT c.conjunto_id, c.valor_Productos, cp.cantidad, p.producto_id,"
                + " p.categoria, p.nombre, p.precio, p.altura, p.color, p.material "
                + "FROM conjuntos AS c "
                + "LEFT JOIN conjuntos_has_products AS cp  ON c.conjunto_id = cp.conjunto_id "
                + "LEFT JOIN productos AS p ON cp.producto_id = p.producto_id "
                + "WHERE c.conjunto_id <= "+Categoria.values().length+" "
                + "ORDER BY c.conjunto_id ASC, p.producto_id ASC";
        return getListConjuntos(query);
    }
    
    private List<ConjuntoProductos> getListConjuntos(String query)throws SQLException {       
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        result = statement.executeQuery(query);
        List<ConjuntoProductos> conjuntos = new LinkedList<>();
        while(result.next()){
            conjuntos.add(generateConjunto(result));
        }
        statement.close();
        result.close();
        conexion.close();
        return conjuntos;
    }

    @Override
    public List<ConjuntoProductos> getAllTiquets() throws SQLException {
        query = "SELECT c.conjunto_id, c.valor_Productos, cp.cantidad, p.producto_id,"
                + " p.categoria, p.nombre, p.precio, p.altura, p.color, p.material "
                + "FROM conjuntos AS c "
                + "LEFT JOIN conjuntos_has_products AS cp  ON c.conjunto_id = cp.conjunto_id "
                + "LEFT JOIN productos AS p ON cp.producto_id = p.producto_id "
                + "WHERE c.conjunto_id > "+Categoria.values().length+" "
                + "ORDER BY c.conjunto_id ASC, p.producto_id ASC";
        return getListConjuntos(query);
    }

    @Override
    public void incrementarValorEnStockById(int idStock, float incrementoValor) throws SQLException {        
        assert idStock >= 1 && idStock <= Categoria.values().length;
        conexion = conexionMySQL.getBDConexion();
        query = "UPDATE conjuntos SET valor_Productos = valor_Productos + ? WHERE conjunto_id = ?";
        preparedStatement = conexion.prepareStatement(query); 
        preparedStatement.setFloat(1, incrementoValor);
        preparedStatement.setInt(2, idStock);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conexion.close();
    }
}
