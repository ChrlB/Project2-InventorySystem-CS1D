/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;
import java.sql.*;
/**
 *
 * @author user
 */
public class NewUser extends JFrame{
  JFrame parent;
  Connection conn;
  TextFieldBuilder username_field,
                   full_name_field,
                   password_field,
                   confirm_password_field;
  ResultSet rs;
  String sql;
  PreparedStatement pstmt;
  
  LabelBuilder  username_field_label,
                full_name_field_label,
                password_field_label,
                confirm_password_field_label;
  
  ButtonBuilder confirm_btn, 
                cancel_btn;
  
  NewUser(JFrame parent,Connection conn){
    this.conn = conn;
    this.parent = parent;
    
    username_field = new TextFieldBuilder(false, 130, 50, 320, 50, 15);
    full_name_field = new TextFieldBuilder(false, 130, 130, 320, 50, 15);
    password_field = new TextFieldBuilder(false, 130, 210, 320, 50, 15);
    confirm_password_field = new TextFieldBuilder(false, 130, 290, 320, 50, 15);
    
    username_field_label = new LabelBuilder("Username: ",30,50,100,50,15);
    full_name_field_label= new LabelBuilder("Full name: ",30,130,100,50,15);
    password_field_label= new LabelBuilder("Password: ",30,210,100,50,15);
    confirm_password_field_label= new LabelBuilder("Confirm: ",30,290,100,50,15);
    
    username_field_label.setForeground(new Color(0XB58863));
    full_name_field_label.setForeground(new Color(0XB58863));
    password_field_label.setForeground(new Color(0XB58863));
    confirm_password_field_label.setForeground(new Color(0XB58863));
    
    confirm_btn = new ButtonBuilder("CONFIRM",30, 370, 200, 50,15);
    cancel_btn = new ButtonBuilder("CANCEL",250, 370, 200, 50,15);
    cancel_btn.addActionListener((a) -> {cancelAndClose();});
    
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setLayout(null);
    this.setSize(500,500);
    this.getContentPane().setBackground(new Color(0x293A3E));
    this.setResizable(false);
    
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        cancelAndClose();
      }
    });
    
    this.add(username_field);
    this.add(full_name_field);
    this.add(password_field);
    this.add(confirm_password_field);
    
    this.add(username_field_label);
    this.add(full_name_field_label);
    this.add(password_field_label);
    this.add(confirm_password_field_label);
    
    this.add(confirm_btn);
    this.add(cancel_btn);
    
    this.setVisible(true);
//    this.add (username_field);
  }
  
  void cancelAndClose(){
    parent.setEnabled(true);
    dispose();
  }
}
