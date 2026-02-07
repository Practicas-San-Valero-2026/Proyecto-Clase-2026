package com.svalero.tienda.tienda.dao;

import com.svalero.tienda.tienda.basedatos.BaseDatos;
import com.svalero.tienda.tienda.model.Clientes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    // READ: leer productos de la BD
    public List<Clientes> findAll() {
        String sql = """
                SELECT c.id, c.nombre, c.apellidos, c.email, c.fecha_nacimiento, c.telefono
                FROM clientes c
                """;

        List<Clientes> clientes = new ArrayList<>();

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Clientes c = new Clientes();

                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setEmail(rs.getString("email"));
                Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    c.setFechaNacimiento(fechaSql.toLocalDate());
                }
                c.setTelefono(rs.getInt("telefono"));

                clientes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer clientes");
            e.printStackTrace();
        }

        return clientes;
    }

    // CREATE: crear cliente
    public int insert(Clientes c) {

        // Validaciones
        if (c == null) return -1;
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) return -1;
        if (c.getApellidos() == null || c.getApellidos().trim().isEmpty()) return -1;
        if (c.getEmail() == null || c.getEmail().trim().isEmpty()) return -1;

        // Conexión
        String sql = """
                INSERT INTO clientes (nombre, apellidos, fecha_nacimiento, email, telefono)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Rellenar los ? en orden
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            if (c.getFechaNacimiento() != null) {
                ps.setDate(3, java.sql.Date.valueOf(c.getFechaNacimiento()));
            } else {
                ps.setDate(3, null);
            }
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getTelefono());

            // Ejecutar insertar
            int filas = ps.executeUpdate();

            // Recuperar id
            if (filas == 1) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1); // id generado
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar cliente");
            e.printStackTrace();
        }

        return -1;
    }

    // DELETE: borrar cliente
    public boolean deleteById(int id) {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al borrar el cliente");
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: editar cliente
    public boolean update(Clientes c) {

        // Validaciones
        if (c == null) return false;

        // Conexión
        String sql = """
                UPDATE clientes
                SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, email = ?, telefono = ?
                WHERE id = ?
                """;

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Rellenar los ? en orden
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            if (c.getFechaNacimiento() != null) {
                ps.setDate(3, java.sql.Date.valueOf(c.getFechaNacimiento()));
            } else {
                ps.setDate(3, null);
            }
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getTelefono());

            // Ejecutar insertar
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al editar cliente");
            e.printStackTrace();
            return false;
        }
    }

}
