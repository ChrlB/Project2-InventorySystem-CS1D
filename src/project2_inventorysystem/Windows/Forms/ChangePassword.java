/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows.Forms;

import java.awt.Color;
import javax.swing.*;
import project2_inventorysystem.Windows.MyComponents.ButtonBuilder;
import project2_inventorysystem.Windows.MyComponents.LabelBuilder;
import project2_inventorysystem.Windows.MyComponents.TextFieldBuilder;
import project2_inventorysystem.Windows.User;

/**
 *
 * @author user
 */
public class ChangePassword extends JFrame{
  User user_window;
  int userID;
  
  TextFieldBuilder password_field,
                   confirm_password_field;
  
  ButtonBuilder confirm_btn, 
                cancel_btn;
  
  LabelBuilder  password_field_label,
                confirm_password_field_label;
  
  
  
  public ChangePassword(User user_window,int userID){
    this.user_window = user_window;
    this.userID = userID;
    
    
    password_field = new TextFieldBuilder(true, 130, 50, 320, 50, 15);
    confirm_password_field = new TextFieldBuilder(true, 130, 130, 320, 50, 15);
    
    password_field_label= new LabelBuilder("New Password: ",30,50,100,50,15);
    confirm_password_field_label= new LabelBuilder("Confirm: ",30,130,100,50,15);
    
    password_field_label.setForeground(new Color(0XB58863));
    confirm_password_field_label.setForeground(new Color(0XB58863));
    
    confirm_btn = new ButtonBuilder("CONFIRM",30, 210, 200, 50,15);
    cancel_btn = new ButtonBuilder("CANCEL",250, 210, 200, 50,15);
    
    cancel_btn.addActionListener((a) -> {close();});
    confirm_btn.addActionListener((a) -> {addUser();});
    
    
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setLayout(null);
    this.setTitle("CHANGE PASSWORD FORM");
    this.setSize(500,330);
    this.getContentPane().setBackground(new Color(0x293A3E));
    this.setResizable(false);
    
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        user_window.setEnabled(true);
        dispose();
      }
    });
    
    this.add(password_field);
    this.add(confirm_password_field);
    
    this.add(password_field_label);
    this.add(confirm_password_field_label);
    
    this.add(confirm_btn);
    this.add(cancel_btn);
    this.setVisible(true);
  }
  
  void close(){
    
  }
  
  void addUser(){
    
  }
}
