/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.Windows;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JFrame;
import project2_inventorysystem.Windows.MyComponents.Header;

/**
 *
 * @author user
 */
public class Sales extends JFrame{
    int user_ID;
    Connection conn;
    Header header;
    
   public Sales(int userID,Connection conn){
      try{   
         this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            new Dashboard(user_ID,conn); 
            dispose();
          }
        });
          
          
        this.conn = conn;
        user_ID = userID;
        header = new Header();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("USER");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1270,700);
        this.getContentPane().setBackground(new Color(0xD3C3B9));
      
        this.setVisible(true);
      }catch(Exception ex){
        System.out.println(ex);
      }
    }
  
}
