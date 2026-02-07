package com.svalero.tienda.tienda.dao;

import com.svalero.tienda.tienda.basedatos.BaseDatos;
import com.svalero.tienda.tienda.model.Productos;
import org.mariadb.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // READ: leer productos de la BD
    public List<Productos> findAll() {
        String sql = """
                SELECT p.id, p.nombre, p.tipo, p.precio, p.stock, p.descripcion
                FROM productos p
                """;

        List<Productos> productos = new ArrayList<>();

        try (Connection con = BaseDatos.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productos p = new Productos();

                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getFloat("precio"));
                p.setStock(rs.getBoolean("stock"));
                p.setDescripcion(rs.getString("descripcion"));

                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer productos");
            e.printStackTrace();
        }

        return productos;
    }

    // CREATE: nuevo producto
    public int insert(Productos p) {

        // Validaciones
        if (p == null) return -1;
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) return -1;

        // Conexión
        String sql = """
                INSERT INTO productos (nombre, tipo, precio, stock, descripcion)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = BaseDatos.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Rellenar los ? en orden
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setFloat(3, p.getPrecio());
            ps.setBoolean(4, p.isStock());
            ps.setString(5, p.getDescripcion());

            // Ejecutar insertar
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar producto");
            e.printStackTrace();
        }

        return -1;
    }

    // DELETE: borrar producto
    public boolean deleteById(int id) {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection con = BaseDatos.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al borrar el producto");
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: editar producto
    public boolean update(Productos p) {

        // Validaciones
        if (p == null) return false;

        // Conexión
        String sql = """
                UPDATE productos
                SET nombre = ?, tipo = ?, precio = ?, stock = ?, descripcion = ?
                WHERE id = ?
                """;

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Rellenar los ? en orden
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setFloat(3, p.getPrecio());
            ps.setBoolean(4, p.isStock());
            ps.setString(5, p.getDescripcion());
            ps.setInt(6, p.getId());

            // Ejecutar insertar
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al editar producto");
            e.printStackTrace();
            return false;
        }
    }

}
