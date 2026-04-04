/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

/**
 *
 * @author user
 */
import java.awt.Color;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends JFrame {
  PreparedStatement pstmt;
  ResultSet rs ;
  JScrollPane tbl;
  ButtonBuilder login_btn;
  Connection conn;
  String sql;
  
  public Login(Connection conn){
    
    try{
      this.conn = conn;
//      String hashed = BCrypt.hashpw("admin123", BCrypt.gensalt(12));
//      String sql = "insert into users(username,password,fullname) values(?,?,?)";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, "admin");
//      pstmt.setString(2, hashed);
//      pstmt.setString(3, "admin");
//      
//      pstmt.executeUpdate();

      
      
      String pass = "admin123";
      login_btn = new ButtonBuilder("LOGIN",5,10,100,50);
      login_btn.addActionListener((a)-> login("admin",pass));
      
      
//      stmt = conn.createStatement();
//      rs = stmt.executeQuery("Select * from categories");
//      
//      TableBuilder tblb = new TableBuilder(rs,500,400);
//      
//      tbl = new JScrollPane(tblb);
//      tbl.setBounds(0,0,500,400);
//      
//      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      setSize(700,500);
      getContentPane().setBackground(new Color(0x293A3E));

      add(login_btn);
      setVisible(true);
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  void login(String input_username, String input_password){
    int user_ID;
    // Verify a password (do this during login)
    try{
      sql = "SELECT * FROM users WHERE username = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, input_username);
      
      rs = pstmt.executeQuery();
      
      if(rs.next()){
        String hashed_password = rs.getString("password");
        
        if (BCrypt.checkpw(input_password, hashed_password)) {
          user_ID = rs.getInt("userID");
//          sql = "INSERT INTO userlogs(userID) VALUES(?)";
//          pstmt = conn.prepareStatement(sql);
//          pstmt.setInt(1, user_ID);
//          
//          pstmt.executeUpdate();
          
          System.out.println("Login Successful!");
          dispose();
          new Dashboard(user_ID, conn);
          
        } else {
          System.out.println("Invalid Password.");
        }
        
      }else{
        System.out.println("wrong credentials");
      }
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  
  
}
