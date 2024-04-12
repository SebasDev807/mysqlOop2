package com.unimayor.oop2.bibliolampara.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String username = "root";
    private final String password = "aqui_tu_contraseña";
    private final String database = "bd_biblioteca";
    private final String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false";

    public Connection conectar() {
        Connection myConnection = null;

        try {
            Class.forName(driver);
            System.out.println("Conexión exitosa");
            myConnection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return myConnection;
    }

}
