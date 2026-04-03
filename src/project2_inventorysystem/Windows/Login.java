/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

/**
 *
 * @author user
 */
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JScrollPane;

public class Login extends JFrame {
  Statement stmt;
  ResultSet rs ;
  JScrollPane tbl;
  
  public Login(Connection conn){
    try{
      stmt = conn.createStatement();
      rs = stmt.executeQuery("Select * from ");
      
      tbl = new JScrollPane(new TableBuilder(rs,500,400));
      tbl.setBounds(0,0,500,400);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      setSize(700,500);
      
      add(tbl);
      setVisible(true);
      
      
    }catch(Exception ex){
      System.out.print(ex);
    }
  }
}
