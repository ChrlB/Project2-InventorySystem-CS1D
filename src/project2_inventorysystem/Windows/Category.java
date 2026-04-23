/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import project2_inventorysystem.Windows.MyComponents.Header;

/**
 *
 * @author juanv
 */
public class Category extends JFrame{
    int user_ID;
    Connection conn;
    Header header;
    JPanel category_form_panel;
    public Category(int userID,Connection conn){
      try{
          
      
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        
        
        category_form_panel = new JPanel();
        category_form_panel.setLayout(null);
        category_form_panel.setBounds(0,100, 480, 450);
        category_form_panel.setBackground(new Color(0XB58863));
        
        
        
        
        
        
        
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            new Dashboard(user_ID,conn); 
            dispose();
          }
        });
        
      

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("USER");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1270,580);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
        this.setLocationRelativeTo(null);
        
        this.add(header);
        this.add(category_form_panel);
        this.setVisible(true);
        
        
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
    
    
}
