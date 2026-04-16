/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import project2_inventorysystem.Windows.MyComponents.*;


/**
 *
 * @author user
 */
public class User extends JFrame{
    int user_ID;
    Connection conn;
    Header header;
    ButtonBuilder add_btn, 
                  delete_btn,
                  update_btn;
    JPanel user_form_panel;
    TextFieldBuilder user_id_field,
                     username_field,
                     password_field,
                     full_name_field;
    ResultSet rs;
    String sql;
    PreparedStatement pstmt;
    
    TableBuilder users_tbl;
    JScrollPane users_tbl_scrollpane;
    
    
    User(int userID,Connection conn){
      try{
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        add_btn = new ButtonBuilder("ADD",100, 50, 200, 50,14);
        delete_btn = new ButtonBuilder("DELETE",100, 110, 200, 50,14);
        update_btn = new ButtonBuilder("UPDATE",100, 170, 200, 50,14);


        user_id_field = new TextFieldBuilder(false, 100, 280, 200, 50, 14);
        username_field = new TextFieldBuilder(false, 100, 340, 200, 50, 14);
        password_field = new TextFieldBuilder(false, 100, 400, 200, 50, 14);
        full_name_field = new TextFieldBuilder(false, 100, 460, 200, 50, 14);




        user_form_panel = new JPanel();
        user_form_panel.setLayout(null);
        user_form_panel.setBounds(0,100, 500, 550);
        user_form_panel.setBackground(new Color(0XB58863));




        user_form_panel.add(add_btn);
        user_form_panel.add(delete_btn);
        user_form_panel.add(update_btn);
        user_form_panel.add(user_id_field);
        user_form_panel.add(username_field);
        user_form_panel.add(password_field);
        user_form_panel.add(full_name_field);



        sql = """
              SELECT * 
              FROM users
              """;

        pstmt = conn.prepareStatement(sql);
        
        rs = pstmt.executeQuery();
        
        users_tbl = new TableBuilder(rs);
        
        users_tbl_scrollpane = new JScrollPane(users_tbl);
        users_tbl_scrollpane.setBounds(510,150,500,400);






        this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            new Dashboard(user_ID,conn); 
            dispose();
          }
        });

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("User");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1270,650);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
        
        this.add(header);
        this.add(user_form_panel);
        this.add(users_tbl_scrollpane);
        this.setVisible(true);
        
      }catch(Exception ex){
        System.out.println(ex);
      }
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
