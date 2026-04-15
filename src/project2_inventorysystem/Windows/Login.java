/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

/**
 *
 * @author user
 */
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import java.awt.Color;
import javax.swing.JFrame;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends JFrame {
  PreparedStatement pstmt;
  ResultSet rs ;
  ButtonBuilder login_btn;
  Connection conn;
  String sql;
  
  public Login(Connection conn){
    
    try{
      this.conn = conn;
      String username = "admin";
      String pass = "admin123";
      
      login_btn = new ButtonBuilder("LOGIN",5,10,100,50,14);
      login_btn.addActionListener((a)-> login(username,pass));
       
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(null);
      this.setSize(700,500);
      this.getContentPane().setBackground(new Color(0x293A3E));

      this.add(login_btn);
      this.setVisible(true);
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  void login(String input_username, String input_password){
    int user_ID;
    
    try{
      rs = getUserInfo(input_username);
      
      // if their`s a record 
      if(rs.next()){
        String hashed_password = rs.getString("password");
        
        // hash the inputed password then compare to 
        // hashed password of that user istored in database 
        if (BCrypt.checkpw(input_password, hashed_password)) {
          user_ID = rs.getInt("userID");
          
          //recordUserLog(user_ID);
          new Dashboard(user_ID, conn);
          this.dispose();
        }else { 
          System.out.println("Invalid Password."); 
        }
        
      }else{ 
        System.out.println("wrong credentials"); 
      }
      
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  ResultSet getUserInfo(String input_username){
    try{
      sql = "SELECT * FROM users WHERE username = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, input_username);
      
      //execute the sql command then store the result to ResultSet object
      return pstmt.executeQuery();
      
    }catch(SQLException ex){
      return null;
    }
    
  }
  
  void recordUserLog(int user_ID){
    try{
      sql = "INSERT INTO userlogs(userID) VALUES(?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, user_ID);

      pstmt.executeUpdate();
      System.out.println("Login Successful!");
    }catch(Exception ex){
      
    }
    
  }
  
  
}
