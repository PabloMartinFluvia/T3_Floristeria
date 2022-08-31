package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoUnidad;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoUnidadDao;
import java.sql.SQLException;
import java.sql.Types;

public class ProductoUnidadMySQL extends GenericDaoMySQL<ProductoUnidad, Integer> implements ProductoUnidadDao {

    @Override
    public void createNuevoProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws SQLException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        assert productoUnidad != null;
        try {
            conexion = conexionMySQL.getBDConexion();
            conexion.setAutoCommit(false);
            addNewProduct(productoUnidad.getProducto());
            productoUnidad.getProducto().setProducto_id(getLastProductId());
            asociarProductoUnidadEnStock(productoUnidad, stock_id);
            conexion.commit();
            preparedStatement.close();
            conexion.close();
        } catch (SQLException exception) {
            if (conexion != null) {
                conexion.rollback();
            }
            throw exception;
        }
    }

    private void addNewProduct(ProductoCompleto producto) throws SQLException {
        query = "INSERT INTO productos (categoria, nombre, precio, altura, color, material) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, producto.getCategoria().name());
        preparedStatement.setString(2, producto.getNombre());
        preparedStatement.setFloat(3, producto.getPrecio());
        preparedStatement.setNull(4, Types.VARCHAR);
        preparedStatement.setNull(5, Types.VARCHAR);
        preparedStatement.setNull(6, Types.VARCHAR);
        //preparedStatement.setString(4, null);
        //preparedStatement.setString(5, null);
        //preparedStatement.setString(6, null);
        if (producto.getAltura() != null) {
            preparedStatement.setString(4, producto.getAltura().name());
        }
        if (producto.getColor() != null) {
            preparedStatement.setString(5, producto.getColor());
        }
        if (producto.getMaterial() != null) {
            preparedStatement.setString(6, producto.getMaterial().name());
        }
        preparedStatement.executeUpdate();
    }

    private int getLastProductId() throws SQLException {
        query = "SELECT producto_id FROM productos ORDER BY producto_id DESC LIMIT 1";
        preparedStatement = conexion.prepareStatement(query);
        result = preparedStatement.executeQuery();
        int id = 0;
        if (result.next()) {
            id = result.getInt(1);
        }
        if (id > 0) {
            return id;
        } else {
            throw new SQLException("Error. Product has not been inserted corretly.");
        }
    }

    private void asociarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws SQLException {
        query = "INSERT INTO conjuntos_has_products (conjunto_id, producto_id, cantidad) VALUES (?, ?, ?)";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, stock_id);
        preparedStatement.setInt(2, productoUnidad.idProducto());
        preparedStatement.setInt(3, productoUnidad.getCantidad());
        preparedStatement.executeUpdate();
    }

    @Override
    public void reCatalogarProductoUnidadEnStock(ProductoUnidad productoUnidad, int stock_id) throws SQLException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        assert productoUnidad != null;
        conexion = conexionMySQL.getBDConexion();
        asociarProductoUnidadEnStock(productoUnidad, stock_id);
        preparedStatement.close();
        conexion.close();
    }

    @Override
    public int getCantidadEnStockBy(int stock_id, int producto_id) throws SQLException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        assert producto_id > 0;
        conexion = conexionMySQL.getBDConexion();
        query = "SELECT cantidad FROM conjuntos_has_products WHERE conjunto_id = ? AND producto_id = ?";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, stock_id);
        preparedStatement.setInt(2, producto_id);
        result = preparedStatement.executeQuery();
        int cantidad = -1; // default if product not found in this stock
        if (result.next()) {
            cantidad = result.getInt(1);
        }
        preparedStatement.close();
        result.close();
        conexion.close();
        return cantidad;
    }

    @Override
    public void incrementarCantidadByStockIdProductoId(int stockId, int productoId, int incremento) throws SQLException {
        assert stockId >= 1 && stockId <= Categoria.values().length;
        assert productoId > 0;
        conexion = conexionMySQL.getBDConexion();
        query = "UPDATE conjuntos_has_products SET cantidad = cantidad+? WHERE conjunto_id = ? AND producto_id = ?";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, incremento);
        preparedStatement.setInt(2, stockId);
        preparedStatement.setInt(3, productoId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conexion.close();
    }

    @Override
    public boolean isSoldAnytimeById(int productoId) throws SQLException {
        assert productoId > 0;
        conexion = conexionMySQL.getBDConexion();
        query = "SELECT * FROM conjuntos_has_products WHERE conjunto_id > ? AND producto_id = ?";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, Categoria.values().length);
        preparedStatement.setInt(2, productoId);
        result = preparedStatement.executeQuery();
        boolean productSold = false;
        if (result.next()) {
            productSold = true;
        }
        preparedStatement.close();
        conexion.close();
        return productSold;
    }

    @Override
    public void eliminarRelacionConStock(int stock_id, int producto_id) throws SQLException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        assert producto_id > 0;
        conexion = conexionMySQL.getBDConexion();
        query = "DELETE FROM conjuntos_has_products WHERE conjunto_id = ? AND producto_id = ?";
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, stock_id);
        preparedStatement.setInt(2, producto_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conexion.close();
    }

}
