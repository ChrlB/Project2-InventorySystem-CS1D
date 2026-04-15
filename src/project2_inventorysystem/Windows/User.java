/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import javax.swing.JFrame;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import project2_inventorysystem.Windows.MyComponents.*;

/**
 *
 * @author user
 */
public class User extends JFrame{
    Header header;
    User(Connection conn){
        header = new Header();
        
        
        
        
        
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("User");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1270,650);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
        this.add(header);
        this.setVisible(true);
    }
    
    
//  String hashed = BCrypt.hashpw("admin123", BCrypt.gensalt(12));
//      String sql = "insert into users(username,password,fullname) values(?,?,?)";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, "admin");
//      pstmt.setString(2, hashed);
//      pstmt.setString(3, "admin");
//      
//      pstmt.executeUpdate();
}
