/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author Hp EliteBook
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public abstract class Formulario extends JFrame implements ActionListener {
    
    protected JButton btnInsertar;
    protected JButton btnConsultar;
    protected JButton btnModificar;
    protected JButton btnEliminar;

    protected JTable tabla;
    protected DefaultTableModel modeloTabla;

    public Formulario(String titulo) {
        setTitle(titulo);
        setSize(1200, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setPreferredSize(new Dimension(200, 0));

        btnInsertar = new JButton("Insertar");
        btnConsultar = new JButton("Consultar todos");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        btnInsertar.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);

        panelBotones.add(btnInsertar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.WEST);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        add(scrollTabla, BorderLayout.CENTER);
    }

    protected void cargarDatos(String[] columnas, Object[][] filas) {
        modeloTabla.setDataVector(filas, columnas);
    }

    protected void limpiarTabla(String[] columnas) {
        modeloTabla.setDataVector(new Object[0][0], columnas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInsertar) {
            insertar();
        } else if (e.getSource() == btnConsultar) {
            consultar();
        } else if (e.getSource() == btnModificar) {
            modificar();
        } else if (e.getSource() == btnEliminar) {
            eliminar();
        }
    }

    public abstract void insertar();
    public abstract void consultar();
    public abstract void modificar();
    public abstract void eliminar();
    }