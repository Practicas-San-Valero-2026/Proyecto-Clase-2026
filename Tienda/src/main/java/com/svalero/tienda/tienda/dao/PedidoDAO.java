package com.svalero.tienda.tienda.dao;

import com.svalero.tienda.tienda.basedatos.BaseDatos;
import com.svalero.tienda.tienda.model.Pedidos;
import com.svalero.tienda.tienda.model.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // READ: Pedidos con nombre del cliente
    public List<Pedidos> findAll() {

        String sql = """
                SELECT p.id, p.numero_pedido, p.precio, p.fecha_pedido, p.entregado,
                       p.observaciones, CONCAT(c.nombre, ' ', c.apellidos) AS cliente, p.id_cliente
                FROM pedidos p
                JOIN clientes c ON c.id = p.id_cliente
                """;

        List<Pedidos> pedidos =new ArrayList<>();

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedidos p = new Pedidos();

                p.setId(rs.getInt("id"));
                p.setNumPedido(rs.getString("numero_pedido"));
                p.setPrecio(rs.getFloat("precio"));
                Date fechaSql = rs.getDate("fecha_pedido");
                if (fechaSql != null) {
                    p.setFechaPedido(fechaSql.toLocalDate());
                }
                p.setEntregado(rs.getBoolean("entregado"));
                p.setObservaciones(rs.getString("observaciones"));
                p.setIdCliente(rs.getInt("id_cliente"));

                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer pedidos");
            e.printStackTrace();
        }

        return pedidos;
    }

    // CREATE: crear pedido
    public int insert(Pedidos p) {

        // Validaciones
        if (p == null) return -1;
        if (p.getNumPedido() == null) return -1;
        if (p.getFechaPedido() == null) return -1;

        // Conexión
        String sql = """
                INSERT INTO pedidos (numero_pedido, precio, fecha_pedido, entregado, observaciones, id_cliente)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Rellenar los ? en orden
            ps.setString(1, p.getNumPedido());
            ps.setFloat(2, p.getPrecio());
            if (p.getFechaPedido() != null) {
                ps.setDate(3, java.sql.Date.valueOf(p.getFechaPedido()));
            } else {
                ps.setDate(3, null);
            }
            ps.setBoolean(4, p.isEntregado());
            ps.setString(5, p.getObservaciones());
            ps.setInt(6, p.getIdCliente());

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
            System.err.println("Error al insertar pedido");
            e.printStackTrace();
        }

        return -1;
    }

    // DELETE: borrar pedido
    public boolean deleteById(int id) {

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al borrar el pedido");
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: editar pedido
    public boolean update(Pedidos p) {

        // Validaciones
        if (p == null) return false;

        // Conexión
        String sql = """
                UPDATE pedidos
                SET numero_pedido = ?, precio = ?, fecha_pedido = ?, entregado = ?, observaciones = ?
                WHERE id = ?
                """;

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Rellenar los ? en orden
            ps.setString(1, p.getNumPedido());
            ps.setFloat(2, p.getPrecio());
            if (p.getFechaPedido() != null) {
                ps.setDate(3, java.sql.Date.valueOf(p.getFechaPedido()));
            } else {
                ps.setDate(3, null);
            }
            ps.setBoolean(4, p.isEntregado());
            ps.setString(5, p.getObservaciones());
            ps.setInt(6, p.getId());

            // Ejecutar insertar
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al editar pedido");
            e.printStackTrace();
            return false;
        }
    }
}
