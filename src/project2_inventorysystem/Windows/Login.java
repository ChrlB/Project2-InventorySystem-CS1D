/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

/**
 *
 * @author user
 */
import project2_inventorysystem.Windows.MyComponents.*;
import java.awt.Color;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JPasswordField;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends JFrame {
  PreparedStatement pstmt;
  ResultSet rs ;
  ButtonBuilder login_btn;
  Connection conn;
  String sql;
  TextFieldBuilder username_field;
  JPasswordField password_field;
  
  
  public Login(Connection conn){
    
    try{
      this.conn = conn;
//      String username = "admin";
//      String pass = "admin123";
      
      username_field = new TextFieldBuilder (true ,50, 100 ,200,50,15);
      password_field = new JPasswordField (15);
      password_field.setEchoChar('*');
      password_field.setBounds(50, 160, 200, 50);
      
      login_btn = new ButtonBuilder("LOGIN",260,150,100,50,14);
      login_btn.addActionListener((a)-> login());
      
     
       
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(null);
      this.setSize(700,500);
      this.getContentPane().setBackground(new Color(0x293A3E));
      this.add(password_field);
      this.add(login_btn);
      this.setVisible(true);
      this.add (username_field);
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  void login(){
    int user_ID;
    
    try{
      String input_username = username_field.getText();
      String input_password = new String(password_field.getPassword());
      
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
