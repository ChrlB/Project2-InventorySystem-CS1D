/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import project2_inventorysystem.Windows.MyComponents.*;
import java.awt.Color;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author user
 */
public class Product extends JFrame{
  Header header;
  JPanel product_form_panel;
  ButtonBuilder add_btn,
                delete_btn,
                update_btn,
                restock_btn;
    Product(Connection conn){
      header = new Header();
      product_form_panel = new JPanel();
      product_form_panel.setBounds(0, 100, 500, 550);
      product_form_panel.setBackground(Color.red);
      
      
      
      
      
      
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("PRODUCT");
      this.setLayout(null);
      this.setResizable(false);
      this.setSize(1270,650);
      this.getContentPane().setBackground(new Color(0xD3C3B9));
      this.add(header);
      this.add(product_form_panel);
      this.setVisible(true);
  }
}
