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
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends JFrame {
  PreparedStatement pstmt;
  ResultSet rs ;
  ButtonBuilder login_btn;
  JButton show_password_btn;
  Connection conn;
  String sql;
  TextFieldBuilder username_field;
  JPasswordField password_field;
  Header header;
  IconBuilder hidden_icon,
          logo_icon;
  LabelBuilder username_label,
               password_label;
  
  ImageIcon scaled_hidden_icon,
            scaled_show_icon;
  boolean isPasswordHidden = true;
  
  public Login(Connection conn){
    
    try{
      this.conn = conn;
      
      header = new Header();
      
      ImageIcon hidden_icon = new ImageIcon(getClass().getResource("/project2_inventorysystem/Windows/Icons/hidden.png"));
      ImageIcon show_icon = new ImageIcon(getClass().getResource("/project2_inventorysystem/Windows/Icons/show.png"));
      
      scaled_hidden_icon = new ImageIcon(hidden_icon.getImage().getScaledInstance(20, 25, Image.SCALE_SMOOTH));
      scaled_show_icon = new ImageIcon(show_icon.getImage().getScaledInstance(20, 25, Image.SCALE_SMOOTH));
      
      show_password_btn = new JButton(null,scaled_hidden_icon);
      show_password_btn.setBounds(378,220,32,50);
      show_password_btn.setFocusable(false);
      show_password_btn.addActionListener((a) -> showHidePassword());
      
      username_field = new TextFieldBuilder (true ,125, 150 ,285,50,15);
      password_field = new JPasswordField (15);
      password_field.setEchoChar('*');
      password_field.setBounds(125, 220, 250, 50);
      password_field.setFont(new Font("Arial", Font.BOLD, 16));
      password_field.setForeground(new Color(0x10232A));
      
      login_btn = new ButtonBuilder("LOGIN",30,290,380,60,17);
      login_btn.addActionListener((a)->  login() );
      
      username_label = new LabelBuilder("Username: ",30,150,200,50,17);
      password_label = new LabelBuilder("Password: ",30,220,200,50,17);
     
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(null);
      this.setSize(470,420);
      this.setResizable(false);
      this.setTitle("LOGIN");
      this.getContentPane().setBackground(new Color(0XB58863));
      this.setLocationRelativeTo(null);
      
      this.add(header);
      
      this.add(password_field);
      this.add (username_field);
      
      this.add(login_btn);
      this.add(show_password_btn);
      
      this.add(username_label);
      this.add(password_label);
      
      this.setVisible(true);
      
      
    }catch(Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  void showHidePassword(){
    if(isPasswordHidden){
      show_password_btn.setIcon(scaled_show_icon);
      isPasswordHidden = false;
      password_field.setEchoChar((char)0);
    }else{
      show_password_btn.setIcon(scaled_hidden_icon);
      isPasswordHidden = true;
      password_field.setEchoChar('*');
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
        JOptionPane.showMessageDialog(null, "wrong credentials", "", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("wrong credentials"); 
      }
      
    }catch (Exception ex){
      System.out.print(ex.getCause());
    }
  }
  
  ResultSet getUserInfo(String input_username){
    try{
      sql = "SELECT * FROM tbl_users WHERE username = ?";
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
      sql = "INSERT INTO tbl_userlogs(userID) VALUES(?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, user_ID);

      pstmt.executeUpdate();
      System.out.println("Login Successful!");
    }catch(Exception ex){
      
    }
    
  }
  
  
}
