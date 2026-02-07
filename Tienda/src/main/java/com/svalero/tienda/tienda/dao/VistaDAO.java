package com.svalero.tienda.tienda.dao;

import com.svalero.tienda.tienda.basedatos.BaseDatos;
import com.svalero.tienda.tienda.model.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VistaDAO {

    public List<Vista> readAll() {
        String sql = """
            SELECT p.numero_pedido, p.entregado, p.fecha_pedido, p.precio, p.observaciones,
                   c.nombre, c.apellidos, c.fecha_nacimiento, c.email, c.telefono
            FROM pedidos p
            JOIN clientes c ON c.id = p.id_cliente
            ORDER BY p.id DESC
            """;

        List<Vista> lista = new ArrayList<>();

        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LocalDate fechaPedido = rs.getDate("fecha_pedido") != null
                        ? rs.getDate("fecha_pedido").toLocalDate()
                        : null;

                LocalDate fechaNac = rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate()
                        : null;

                Vista v = new Vista(
                        rs.getString("numero_pedido"),
                        rs.getBoolean("entregado"),
                        fechaPedido,
                        rs.getFloat("precio"),
                        rs.getString("observaciones"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        fechaNac,
                        rs.getString("email"),
                        rs.getInt("telefono")
                );

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
