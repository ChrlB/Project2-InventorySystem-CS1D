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
  int user_ID;
  Connection conn;
  Header header;
  JPanel product_form_panel;
  ButtonBuilder add_btn,
                delete_btn,
                update_btn,
                restock_btn;
    Product(int userID, Connection conn){
      this.conn = conn;
      user_ID = userID;
      header = new Header();
      
      add_btn = new ButtonBuilder("ADD", 50, 50, 200, 50, 15);
      delete_btn = new ButtonBuilder("DELETE", 50, 110, 200, 50, 15);
      update_btn = new ButtonBuilder("UPDATE", 50, 170, 200, 50, 15);
      restock_btn = new ButtonBuilder("RESTOCK", 50, 230, 200, 50, 15);
      
      product_form_panel = new JPanel();
      product_form_panel.setBounds(0, 100, 500, 550);
      product_form_panel.setBackground (new Color(0XB58863));
      product_form_panel.setLayout(null);
      
      product_form_panel.add(add_btn);
      product_form_panel.add(delete_btn);
      product_form_panel.add(update_btn);
      product_form_panel.add(restock_btn);
      
      
      
      
      this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
          new Dashboard(user_ID,conn); 
          dispose();
        }
      });
      
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
