/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import project2_inventorysystem.Windows.Dashboard;
import javax.swing.JFrame;
import java.sql.*;

/**
 *
 * @author user
 */
public class Order extends JFrame{
  int user_ID;
  PreparedStatement pstmt;
  Connection conn;
  ResultSet rs;
  String sql;
  
  public Order(int userID,Connection conn){
    this.conn = conn;
    user_ID = userID;
    
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setTitle("ORDER");
    setLayout(null);
    setResizable(false);
    setSize(1070,550);
    getContentPane().setBackground(new Color(0xD3C3B9));
    
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        new Dashboard(user_ID,conn); // Call your method here
        dispose();
      }
    });
    
    setVisible(true);
  }
}
