/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.*;

/**
 *
 * @author user
 */
public class Dashboard extends JFrame{
  int user_ID;
  PreparedStatement pstmt;
  JPanel header;
  Connection conn;
  String sql;
  
  public Dashboard(int userID, Connection conn){
    this.conn = conn;
    
    user_ID = userID;
    header = new JPanel();
    header.setBounds(0,0,600,100);
    header.setBackground(new Color(40,40,35));
    
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setLayout(null);
    setSize(600,500);
    getContentPane().setBackground(new Color(60,60,70));
    
    

    // 2. Add the listener to call your method
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        logout(user_ID); // Call your method here
      }
    });
    
    add(header);
    setVisible(true);
  }
  
  void logout(int user_ID){
    // Verify a password (do this during login)
    try{
//      sql = "UPDATE userlogs SET logoutDate = CURRENT_TIMESTAMP WHERE userID = ? AND logoutDate IS NULL";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setInt(1, user_ID);
//      pstmt.executeUpdate();
//
      System.out.println("Logout Successful!");
      dispose();
          
        
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
}
