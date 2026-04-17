/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    ButtonBuilder new_btn, 
                  delete_btn,
                  change_password_btn,
                  update_btn;
    JPanel user_form_panel;
    TextFieldBuilder user_id_field,
                     username_field,
                     full_name_field;
    ResultSet rs;
    String sql;
    PreparedStatement pstmt;
    
    TableBuilder users_tbl;
    JScrollPane users_tbl_scrollpane;
    
    LabelBuilder  user_id_field_label,
                  username_field_label,
                  full_name_field_label;
    
    Object[] selected_record;
    
    User(int userID,Connection conn){
      try{
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        
        
        user_id_field_label = new LabelBuilder("User ID: ",30,50,100,50,15);
        username_field_label= new LabelBuilder("Username: ",30,130,100,50,15);
        full_name_field_label= new LabelBuilder("Fullname: ",30,210,100,50,15);
        
        
        user_id_field = new TextFieldBuilder(false, 130, 50, 320, 50, 15);
        username_field = new TextFieldBuilder(true, 130, 130, 320, 50, 15);
        //password_field = new TextFieldBuilder(false, 200, 210, 250, 50, 15);
        full_name_field = new TextFieldBuilder(true, 130, 210, 320, 50, 15);
        
        
        new_btn = new ButtonBuilder("NEW",30, 370, 200, 50,15);
        update_btn = new ButtonBuilder("UPDATE",250, 370, 200, 50,15);
        change_password_btn = new ButtonBuilder("CHANGE PASSWORD",30, 450, 200, 50,15);
        delete_btn = new ButtonBuilder("DELETE",250, 450, 200, 50,15);
        
        

        user_form_panel = new JPanel();
        user_form_panel.setLayout(null);
        user_form_panel.setBounds(0,100, 480, 650);
        user_form_panel.setBackground(new Color(0XB58863));


        user_form_panel.add(new_btn);
        user_form_panel.add(delete_btn);
        user_form_panel.add(change_password_btn);
        user_form_panel.add(update_btn);
        
        user_form_panel.add(user_id_field_label);
        user_form_panel.add(username_field_label);
        user_form_panel.add(full_name_field_label);
        
        user_form_panel.add(user_id_field);
        user_form_panel.add(username_field);
        //user_form_panel.add(password_field);
        user_form_panel.add(full_name_field);



        sql = """
              SELECT * 
              FROM users
              """;

        pstmt = conn.prepareStatement(sql);
        
        rs = pstmt.executeQuery();
        
        users_tbl = new TableBuilder(rs);
        
        users_tbl.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {  
              showSelectedRecord();
          }
        });
        
        users_tbl_scrollpane = new JScrollPane(users_tbl);
        users_tbl_scrollpane.setBounds(500,150,725,500);



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
        this.setSize(1270,700);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
        
        this.add(header);
        this.add(user_form_panel);
        this.add(users_tbl_scrollpane);
        this.setVisible(true);
        
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
    
    void showSelectedRecord(){
      try{
        int row = users_tbl.getSelectedRow();

        if (row != -1) {

          selected_record = new Object[] {
            users_tbl.getValueAt(row, 0),
            users_tbl.getValueAt(row, 1), 
            users_tbl.getValueAt(row, 2), 
            users_tbl.getValueAt(row, 3)  
          };
        }
        user_id_field.setText(""+selected_record[0]);
        username_field.setText(""+selected_record[1]);
        full_name_field.setText(""+selected_record[3]);
      }catch(Exception ex){
        System.out.print(ex.getCause());
      }
    };
    
    
//  String hashed = BCrypt.hashpw("admin123", BCrypt.gensalt(12));
//      String sql = "insert into users(username,password,fullname) values(?,?,?)";
//      pstmt = conn.prepareStatement(sql);
//      pstmt.setString(1, "admin");
//      pstmt.setString(2, hashed);
//      pstmt.setString(3, "admin");
//      
//      pstmt.executeUpdate();
}
