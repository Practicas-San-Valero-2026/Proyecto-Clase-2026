package com.svalero.tienda.tienda.basedatos;

import java.sql.*;

public class BaseDatos {
    // Datos de conexión
    private static final String URL  = "jdbc:mariadb://localhost:3306/tienda";
    private static final String USER = "root";
    private static final String PASS = "1234";

    // Metod devuelve una conexión lista para usar
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

