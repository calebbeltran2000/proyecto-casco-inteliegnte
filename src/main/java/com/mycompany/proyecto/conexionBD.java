/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    

    public static Connection conectar() {
        String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=proyecto;"
                    + "trustServerCertificate=true;";

        String usuario = "usuario java";
        String contrasena = "12345";

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return conexion;
    }
    }
