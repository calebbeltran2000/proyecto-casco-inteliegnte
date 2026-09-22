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


public abstract class Formulario extends JFrame implements ActionListener {

    protected JButton btnInsertar;
    protected JButton btnConsultar;
    protected JButton btnModificar;
    protected JButton btnEliminar;

    public Formulario(String titulo) {
        setTitle(titulo);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        btnInsertar = new JButton("Insertar");
        btnConsultar = new JButton("Consultar todos");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        btnInsertar.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);

        add(btnInsertar);
        add(btnConsultar);
        add(btnModificar);
        add(btnEliminar);
    }

    
    public abstract void insertar();
    
    public abstract void consultar();
    public abstract void modificar();
    public abstract void eliminar();  
    
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInsertar) {
            insertar();
        }else if (e.getSource() == btnConsultar) {
            consultar();
        } else if (e.getSource() == btnModificar) {
            modificar();
        } else if (e.getSource() == btnEliminar) {
            eliminar();
        }
    }

    
      
}
