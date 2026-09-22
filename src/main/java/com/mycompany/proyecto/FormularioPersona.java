/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import javax.swing.*;
import java.sql.*;

public class FormularioPersona extends Formulario {

    public FormularioPersona() {
        super("Gestion de Personas");
    }

    private void ejecutar(String sql, String mensajeExito) {
        try {
            Connection con = conexionBD.conectar();
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            JOptionPane.showMessageDialog(this, mensajeExito);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    public void insertar() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String edad = JOptionPane.showInputDialog(this, "Edad:");
        ejecutar("INSERT INTO persona ([nombre persona], edad) VALUES ('" + nombre + "', " + edad + ")", "Persona insertada");
    }
    
    @Override
    public void modificar() {
        String id = JOptionPane.showInputDialog(this, "ID a modificar:");
        String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre:");
        String edad = JOptionPane.showInputDialog(this, "Nueva edad:");
        ejecutar("UPDATE persona SET [nombre persona]='" + nombre + "', edad=" + edad + " WHERE [ID persona]=" + id, "Persona modificada");
    }

    @Override
    public void eliminar() {
        String id = JOptionPane.showInputDialog(this, "ID a eliminar:");
        ejecutar("DELETE FROM persona WHERE [ID persona]=" + id, "Persona eliminada");
    }

    @Override
    public void consultar() {
        String resultado = "";
        try {
            Connection con = conexionBD.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM persona");
            while (rs.next()) {
                resultado += "ID: " + rs.getInt("ID persona")
                        + " | Nombre: " + rs.getString("nombre persona")
                        + " | Edad: " + rs.getInt("edad") + "\n";
            }
            con.close();
            JOptionPane.showMessageDialog(this, resultado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

       }
