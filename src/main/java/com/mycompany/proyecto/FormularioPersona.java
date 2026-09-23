/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import javax.swing.*;
import java.sql.*;

public class FormularioPersona extends Formulario {

    public FormularioPersona() {
        super("Gestion de Accidentes");
        actualizarTabla();
    }

    private void actualizarTabla() {
        try {
            Connection con = conexionBD.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT a.[ID accidente], p.[nombre persona], v.[tipo de vehiculo], l.[descripcion del lugar] "
                + "FROM accidente a "
                + "JOIN persona p ON a.[ID persona] = p.[ID persona] "
                + "JOIN vehiculo v ON a.[ID vehiculo] = v.[ID vehiculo] "
                + "JOIN lugar l ON a.[ID lugar] = l.[ID lugar]"
            );

            java.util.List<Object[]> lista = new java.util.ArrayList<>();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("ID accidente"),
                    rs.getString("nombre persona"),
                    rs.getString("tipo de vehiculo"),
                    rs.getString("descripcion del lugar")
                });
            }
            con.close();

            Object[][] filas = lista.toArray(new Object[0][]);
            String[] columnas = {"ID Accidente", "Persona", "Vehiculo", "Lugar"};
            cargarDatos(columnas, filas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla: " + e.getMessage());
        }
    }

    @Override
    public void insertar() {
        String idPersona = JOptionPane.showInputDialog(this, "ID de la persona:");
        String tipoVehiculo = JOptionPane.showInputDialog(this, "Tipo de vehiculo:");
        String lugar = JOptionPane.showInputDialog(this, "Lugar del accidente:");

        try {
            Connection con = conexionBD.conectar();

            PreparedStatement psVehiculo = con.prepareStatement(
                "INSERT INTO vehiculo ([tipo de vehiculo], modelo) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            psVehiculo.setString(1, tipoVehiculo);
            psVehiculo.setString(2, "");
            psVehiculo.executeUpdate();
            ResultSet rsVehiculo = psVehiculo.getGeneratedKeys();
            rsVehiculo.next();
            int idVehiculo = rsVehiculo.getInt(1);

            PreparedStatement psLugar = con.prepareStatement(
                "INSERT INTO lugar ([descripcion del lugar], direccion) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            psLugar.setString(1, lugar);
            psLugar.setString(2, "");
            psLugar.executeUpdate();
            ResultSet rsLugar = psLugar.getGeneratedKeys();
            rsLugar.next();
            int idLugar = rsLugar.getInt(1);

            PreparedStatement psAccidente = con.prepareStatement(
                "INSERT INTO accidente ([ID persona], [ID vehiculo], [ID lugar]) VALUES (?, ?, ?)"
            );
            psAccidente.setInt(1, Integer.parseInt(idPersona));
            psAccidente.setInt(2, idVehiculo);
            psAccidente.setInt(3, idLugar);
            psAccidente.executeUpdate();

            con.close();
            JOptionPane.showMessageDialog(this, "Accidente insertado");
            actualizarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    public void modificar() {
        String idAccidente = JOptionPane.showInputDialog(this, "ID de accidente a modificar:");
        String tipoVehiculo = JOptionPane.showInputDialog(this, "Nuevo tipo de vehiculo:");
        String lugar = JOptionPane.showInputDialog(this, "Nuevo lugar:");

        try {
            Connection con = conexionBD.conectar();

            PreparedStatement psBuscar = con.prepareStatement(
                "SELECT [ID vehiculo], [ID lugar] FROM accidente WHERE [ID accidente] = ?"
            );
            psBuscar.setInt(1, Integer.parseInt(idAccidente));
            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idVehiculo = rs.getInt("ID vehiculo");
                int idLugar = rs.getInt("ID lugar");

                PreparedStatement psVehiculo = con.prepareStatement(
                    "UPDATE vehiculo SET [tipo de vehiculo] = ? WHERE [ID vehiculo] = ?"
                );
                psVehiculo.setString(1, tipoVehiculo);
                psVehiculo.setInt(2, idVehiculo);
                psVehiculo.executeUpdate();

                PreparedStatement psLugar = con.prepareStatement(
                    "UPDATE lugar SET [descripcion del lugar] = ? WHERE [ID lugar] = ?"
                );
                psLugar.setString(1, lugar);
                psLugar.setInt(2, idLugar);
                psLugar.executeUpdate();

                con.close();
                JOptionPane.showMessageDialog(this, "Accidente modificado");
                actualizarTabla();
            } else {
                con.close();
                JOptionPane.showMessageDialog(this, "No existe ese ID de accidente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {
        String id = JOptionPane.showInputDialog(this, "ID de accidente a eliminar:");
        try {
            Connection con = conexionBD.conectar();
            PreparedStatement ps = con.prepareStatement("DELETE FROM accidente WHERE [ID accidente] = ?");
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Accidente eliminado");
            actualizarTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    public void consultar() {
        actualizarTabla();
    }
}
