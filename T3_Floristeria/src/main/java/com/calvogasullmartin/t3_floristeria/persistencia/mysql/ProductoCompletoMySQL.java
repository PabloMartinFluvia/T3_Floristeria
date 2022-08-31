package com.calvogasullmartin.t3_floristeria.persistencia.mysql;

import com.calvogasullmartin.t3_floristeria.modelos.Altura;
import com.calvogasullmartin.t3_floristeria.modelos.Categoria;
import com.calvogasullmartin.t3_floristeria.modelos.Material;
import com.calvogasullmartin.t3_floristeria.modelos.ProductoCompleto;
import com.calvogasullmartin.t3_floristeria.persistencia.ProductoCompletoDao;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoCompletoMySQL extends GenericDaoMySQL<ProductoCompleto, Integer> implements ProductoCompletoDao {

    @Override
    public int findId(ProductoCompleto producto) throws SQLException {
        conexion = conexionMySQL.getBDConexion();
        String extraQuery ="";        
        String value = "";
        if (producto.getAltura() != null) {
            value = producto.getAltura().name();
            extraQuery ="altura = ? AND color IS NULL AND material IS NULL";
        }else if (producto.getColor() != null){
            value = producto.getColor();
            extraQuery ="altura IS NULL AND color = ? AND material IS NULL";
        } else if(producto.getMaterial() != null){
            value = producto.getMaterial().name();
            extraQuery ="altura IS NULL AND color IS NULL AND material = ?";
        }        
        query = "SELECT producto_id FROM productos WHERE "
                + "categoria = ? AND nombre = ? AND precio = ? AND "+ extraQuery;
        preparedStatement = conexion.prepareStatement(query);
        Categoria categoria = producto.getCategoria();
        preparedStatement.setString(1, categoria.name());
        preparedStatement.setString(2, producto.getNombre());
        preparedStatement.setFloat(3, producto.getPrecio()); 
        preparedStatement.setString(4, value);
        result = preparedStatement.executeQuery();
        int id = 0;
        if (result.next()) {
            id = result.getInt(1);
        }
        preparedStatement.close();
        result.close();
        conexion.close();
        return id;
    }

    @Override
    public ProductoCompleto findProductoByIdInStockId(int producto_id, int stock_id) throws SQLException {
        assert stock_id >= 1 && stock_id <= Categoria.values().length;
        assert producto_id > 0;
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement();
        query = "SELECT p.producto_id, p.categoria, p.nombre, p.precio, p.altura, p.color, p.material "
                + "FROM conjuntos_has_products AS cp JOIN productos AS p ON cp.producto_id = p.producto_id "
                + "WHERE cp.conjunto_id = " + stock_id + " AND p.producto_id = " + producto_id;
        result = statement.executeQuery(query);
        ProductoCompleto producto = null;
        if (result.next() && result.getString("categoria") != null) {
            producto = generateProduct(result);
        } 
        statement.close();
        result.close();
        conexion.close();
        return producto;
    }
    
    public ProductoCompleto generateProduct(ResultSet result)throws SQLException {
            ProductoCompleto producto = new ProductoCompleto();
            producto.setProducto_id(result.getInt("producto_id"));
            producto.setCategoria(Categoria.valueOf(result.getString("categoria")));
            producto.setNombre(result.getString("nombre"));
            producto.setPrecio(result.getFloat("precio"));
            producto.setAltura(null);
            producto.setColor(null);
            producto.setMaterial(null);
            if (result.getString("altura") != null) {
                producto.setAltura(Altura.valueOf(result.getString("altura")));
            }
            if (result.getString("color") != null) {
               producto.setColor(result.getString("color"));
            }
            if (result.getString("material") != null) {
                producto.setMaterial(Material.valueOf(result.getString("material")));
            }
            return producto;
    }

    @Override
    public void eliminarProducto(ProductoCompleto producto) throws SQLException {
        assert producto != null;
        conexion = conexionMySQL.getBDConexion();
        statement = conexion.createStatement();
        int id = producto.getProducto_id();
        query = "DELETE FROM productos WHERE producto_id = " + id;
        statement.executeUpdate(query);
        statement.close();
        conexion.close();
    }

}
